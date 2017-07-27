import numpy as np
import tensorflow as tf

# definitions
FILE_NAME = "newdata0725_variable_interval.csv"
TIME_INTERVAL = 10  # records per second

N_INPUT_LAYER = 10 * TIME_INTERVAL  # 10 seconds historical data
N_PREDICT_FORWARD = 5 * TIME_INTERVAL  # 5 seconds later
START_TIME_POINT = 150  # START_TIME_POINT-th second (previous data will be used later)
N_TRAINING_DATA = 1000 * TIME_INTERVAL  # training record number
N_TESTING_DATA = 100 * TIME_INTERVAL  # testing record number, following training data


# find the beginning of a specific second
def find_second_beg(data_set, start_second):
    beg = 0
    end = len(data_set) - 1
    mid = 0
    while beg <= end:
        mid = (beg + end) // 2
        mid_val = int(np.math.floor(data_set[mid][0]))
        if mid_val < start_second:
            beg = mid + 1
        elif mid_val > start_second:
            end = mid - 1
        else:
            break
    start_idx = min(max(mid, 0), len(data_set) - 1)
    while start_idx >= 0 and data_set[start_idx - 1][0] >= start_second:
        start_idx -= 1
    return start_idx


# fetch data from csv file
# data format:
# [0]: time
# [1]: wave elevation
# [2]: linear heave
# [3]: real heave
# split data for training and testing
# using N values as input, 1 value as output; future real values are used for prediction
raw_data = np.loadtxt(FILE_NAME, delimiter=",")  # data from second 101
RAW_DATA_LEN = len(raw_data)
raw_elevation = raw_data[:, 1]
raw_linear_heave = raw_data[:, 2]
raw_real_heave = raw_data[:, 3]
raw_nonlinear_heave = np.subtract(raw_real_heave, raw_linear_heave)
print(raw_nonlinear_heave[0])

print(raw_data[find_second_beg(raw_data, 101)])
print(raw_data[find_second_beg(raw_data, 120)])

training_data_idx_start = find_second_beg(raw_data, START_TIME_POINT)
testing_data_idx_start = training_data_idx_start + N_TRAINING_DATA
training_input = N_TRAINING_DATA * [None]
training_target = raw_nonlinear_heave[training_data_idx_start: training_data_idx_start + N_TRAINING_DATA]
for i in range(N_TRAINING_DATA):
    training_input[i] = raw_elevation[i: i + N_TRAINING_DATA]
print(training_input[0][0])

testing_input = N_TESTING_DATA * [None]
testing_target = raw_nonlinear_heave[testing_data_idx_start: testing_data_idx_start + N_TESTING_DATA]
for i in range(N_TESTING_DATA):
    testing_input[i] = [raw_data[idx + testing_data_idx_start + i][1] for idx in range(N_TESTING_DATA)]
print(testing_input[0][0])


# config the MLP
# model, loss function, dropout, optimizer

# train

# testing

# NRMSE

# plotting
