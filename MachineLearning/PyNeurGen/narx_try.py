import math
import numpy
from pylab import plot, legend, subplot, grid, xlabel, ylabel, show, title
from pyneurgen.neuralnet import NeuralNet
from pyneurgen.recurrent import NARXRecurrent

MODE = 1
START_SECOND = 20
TRAIN_SECONDS = 50
VALIDATE_SECONDS = 15
TEST_SECONDS = 15
SAMPLE_SECOND = 1.0  # 1.0: 1 second; 0.1: 0.1 second

# detect data file name
FILE_NAME = None
if MODE == 1:
    FILE_NAME = "datasave170715.Chirp.csv"
elif MODE == 2:
    FILE_NAME = "datasave170715.MultiSin.csv"
elif MODE == 3:
    FILE_NAME = "datasave170715.RARP.csv"
else:
    print("Unknown file name!")
    exit(-1)

# load data [time, elevation, heave]
raw_data = numpy.loadtxt(open(FILE_NAME, "rb"), delimiter=",")
# print(raw_data)

# select data
ACCURACY = 0.00001
beg = 0
end = len(raw_data) - 1
mid = 0
while True:
    mid = (beg + end) / 2
    mid_val = int(math.floor(raw_data[mid][0]))
    if mid_val < START_SECOND:
        beg = mid + 1
    elif mid_val > START_SECOND:
        end = mid - 1
    else:
        break
start_idx = mid
while start_idx >= 0 and raw_data[start_idx - 1][0] >= START_SECOND:
    start_idx -= 1
second_length = 1  # the length of each second data, TODO: fix dynamic second length
while start_idx < len(raw_data) and raw_data[start_idx + second_length][0] < START_SECOND + 1:
    second_length += 1
print("second_length = {:.2f}".format(second_length))
if second_length * SAMPLE_SECOND < 1.0:
    print("Sampling rate too large!!! Acceptable rate is {:.2f}".format(1.0 / float(second_length)))
    exit(-1)

pick_every = int(second_length * SAMPLE_SECOND)
start_training_idx = start_idx
end_training_idx = start_training_idx + TRAIN_SECONDS * second_length
start_validate_idx = end_training_idx + 1
end_validate_idx = start_validate_idx + VALIDATE_SECONDS * second_length
start_test_idx = end_validate_idx + 1
end_test_idx = end_validate_idx + TEST_SECONDS * second_length

selected_data = raw_data[start_training_idx:end_test_idx:pick_every]
selected_data_time = [[row[0]] for row in selected_data]
training_data = raw_data[start_training_idx:end_training_idx:pick_every]
validate_data = raw_data[start_validate_idx:end_validate_idx:pick_every]
test_data = raw_data[start_test_idx:end_test_idx:pick_every]

# define NN
input_nodes = 1
hidden_nodes = 10
output_nodes = 1

output_order = 10
incoming_weight_from_output = .6
input_order = 10
incoming_weight_from_input = .4

net = NeuralNet()
net.init_layers(input_nodes, [hidden_nodes], output_nodes, NARXRecurrent(
    output_order,
    incoming_weight_from_output,
    input_order,
    incoming_weight_from_input))
net.randomize_network()
net.set_halt_on_extremes(True)

net.set_random_constraint(.5)
net.set_learnrate(.1)

# net.set_all_inputs(training_data[:, 1])  # this results in [a, b, ..., z] not [[a], [b], ..., [z]]
# net.set_all_targets(training_data[:, 2])
net.set_all_inputs([[row[1]] for row in selected_data])  # wanting [[a], [b], ..., [z]]
net.set_all_targets([[row[2]] for row in selected_data])

length = len(selected_data)
learn_end_point = int((end_validate_idx - start_training_idx) / pick_every * .8)  # validation

net.set_learn_range(0, (end_validate_idx - start_training_idx) / pick_every)
net.set_test_range((end_validate_idx - start_training_idx) / pick_every, (end_test_idx - start_training_idx) / pick_every)
net.layers[1].set_activation_type('tanh')

# train
net.learn(epochs=125, show_epoch_results=True)
mse = net.test()

# test and generate charts
all_real = [item[0] for item in net.test_targets_activations]
all_targets = [item[1] for item in net.test_targets_activations]

plot(selected_data_time[
     (end_validate_idx - start_training_idx) / pick_every: (end_test_idx - start_training_idx) / pick_every],
     all_targets, 'bo', label='predict')
plot(selected_data_time[
     (end_validate_idx - start_training_idx) / pick_every: (end_test_idx - start_training_idx) / pick_every],
     all_real, ':k',
     label='real')
grid(True)
legend(loc='lower left', numpoints=1)
title("Test Target Points vs Actual Points")

show()
