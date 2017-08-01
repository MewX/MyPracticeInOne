"""
References:
    [1] https://gist.github.com/mick001/45a45b94eab29d81a5f1e46d88632053
"""

import matplotlib.pyplot as plt
import numpy as np
import tensorflow as tf
import matplotlib.pyplot as plt

# definitions
FILE_NAME = "newdata0725_variable_interval.csv"
# FILE_NAME = "test.csv"
TIME_INTERVAL = 10  # records per second

N_INPUT_LAYER = 10 * TIME_INTERVAL  # 10 seconds historical data
N_PREDICT_FORWARD = 1 * TIME_INTERVAL  # 5 seconds later
START_TIME_POINT = 150  # START_TIME_POINT-th second (previous data will be used later)
N_TRAINING_DATA = 1000 * TIME_INTERVAL  # training record number
N_TESTING_DATA = 100 * TIME_INTERVAL  # testing record number, following training data

LEARNING_RATE = 0.03
STANDARD_DEVIATION = 0.1
TRAINING_EPOCHS = 1000
BATCH_SIZE = 50  # 100
DISPLAY_STEP = 20
RANDOM_STATE = 100


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
raw_time = np.array(raw_data[:, 0])
raw_elevation = np.array(raw_data[:, 1])
raw_linear_heave = np.array(raw_data[:, 2])
raw_real_heave = np.array(raw_data[:, 3])
raw_nonlinear_heave = np.subtract(raw_real_heave, raw_linear_heave)
print(raw_nonlinear_heave[0])

print(raw_data[find_second_beg(raw_data, 101)])
print(raw_data[find_second_beg(raw_data, 120)])

training_data_idx_start = find_second_beg(raw_data, START_TIME_POINT)
testing_data_idx_start = training_data_idx_start + N_TRAINING_DATA
training_input = N_TRAINING_DATA * [None]
training_target = N_TRAINING_DATA * [None]
for i in range(N_TRAINING_DATA):
    training_input[i] = raw_elevation[i + training_data_idx_start: i + training_data_idx_start + N_INPUT_LAYER]
    training_target[i] = [raw_nonlinear_heave[training_data_idx_start + i]]
training_input = np.array(training_input)
training_target = np.array(training_target)
print(training_input.shape)
print(training_target.shape)

testing_input = N_TESTING_DATA * [None]
testing_target = N_TESTING_DATA * [None]
testing_time = N_TESTING_DATA * [None]
for i in range(N_TESTING_DATA):
    testing_input[i] = raw_elevation[i + testing_data_idx_start: i + testing_data_idx_start + N_INPUT_LAYER]
    testing_target[i] = [raw_nonlinear_heave[testing_data_idx_start + i]]
testing_input = np.array(testing_input)
testing_target = np.array(testing_target)
print(testing_input.shape)
print(testing_target.shape)

# NRMSE
def nrmse(real, predict):
    up = tf.sqrt(tf.reduce_sum(tf.square(real - predict)))
    down = tf.sqrt(tf.reduce_sum(tf.square(real)))
    return up / down


# model, loss function, dropout, optimizer
# Net params
n_input = N_INPUT_LAYER  # input n labels
n_hidden_1 = 100  # 1st layer
n_hidden_2 = 100  # 2nd layer
n_hidden_3 = 100  # 3rd layer
n_hidden_4 = 100  # 4th layer
n_classes = 1  # output 1 result

# Tf placeholders
X = tf.placeholder(tf.float32, [None, n_input])
y = tf.placeholder(tf.float32, [None, n_classes])
dropout_keep_prob = tf.placeholder(tf.float32)


# def mlp(_x, _weights, _biases, dropout_keep_probability):
#     layer1 = tf.nn.dropout(tf.nn.relu(tf.add(tf.matmul(_x, _weights['h1']), _biases['b1'])), dropout_keep_probability)
#     layer2 = tf.nn.dropout(tf.nn.relu(tf.add(tf.matmul(layer1, _weights['h2']), _biases['b2'])),
#                            dropout_keep_probability)
#     layer3 = tf.nn.dropout(tf.nn.relu(tf.add(tf.matmul(layer2, _weights['h3']), _biases['b3'])),
#                            dropout_keep_probability)
#     layer4 = tf.nn.dropout(tf.nn.relu(tf.add(tf.matmul(layer3, _weights['h4']), _biases['b4'])),
#                            dropout_keep_probability)
#     out = tf.nn.relu(tf.add(tf.matmul(layer4, _weights['out']), _biases['out']))
#     return out

def mlp(_x, _weights, _biases):
    layer1 = tf.nn.sigmoid(tf.add(tf.matmul(_x, _weights['h1']), _biases['b1']))
    layer2 = tf.nn.sigmoid(tf.add(tf.matmul(layer1, _weights['h2']), _biases['b2']))
    layer3 = tf.nn.sigmoid(tf.add(tf.matmul(layer2, _weights['h3']), _biases['b3']))
    layer4 = tf.nn.sigmoid(tf.add(tf.matmul(layer3, _weights['h4']), _biases['b4']))
    out = tf.nn.sigmoid(tf.add(tf.matmul(layer4, _weights['out']), _biases['out']))
    return out


weights = {
    'h1': tf.Variable(tf.random_normal([n_input, n_hidden_1], stddev=STANDARD_DEVIATION)),
    'h2': tf.Variable(tf.random_normal([n_hidden_1, n_hidden_2], stddev=STANDARD_DEVIATION)),
    'h3': tf.Variable(tf.random_normal([n_hidden_2, n_hidden_3], stddev=STANDARD_DEVIATION)),
    'h4': tf.Variable(tf.random_normal([n_hidden_3, n_hidden_4], stddev=STANDARD_DEVIATION)),
    'out': tf.Variable(tf.random_normal([n_hidden_4, n_classes], stddev=STANDARD_DEVIATION)),
}

biases = {
    'b1': tf.Variable(tf.random_normal([n_hidden_1])),
    'b2': tf.Variable(tf.random_normal([n_hidden_2])),
    'b3': tf.Variable(tf.random_normal([n_hidden_3])),
    'b4': tf.Variable(tf.random_normal([n_hidden_4])),
    'out': tf.Variable(tf.random_normal([n_classes]))
}

# Build model
pred = mlp(X, weights, biases)

# Accuracy
# correct_prediction = tf.equal(tf.argmax(pred, 1), tf.argmax(y, 1))
# accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))
accuracy = nrmse(y, pred)

# Accuracy
# correct_prediction = tf.equal(tf.argmax(pred, 1), tf.argmax(y, 1))
# accuracy = tf.reduce_mean(tf.cast(correct_prediction, tf.float32))
accuracy = nrmse(y, pred)

# Loss and optimizer
# cost = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=pred, labels=y))  # softmax loss
cost = tf.reduce_mean(tf.pow(y - pred, 2))
# optimizer = tf.train.AdamOptimizer(learning_rate=LEARNING_RATE).minimize(cost)
optimizer = tf.train.GradientDescentOptimizer(learning_rate=LEARNING_RATE).minimize(cost)  # learning rate
# optimizer = tf.train.AdamOptimizer(learning_rate=LEARNING_RATE).minimize(cost)

# Training
print("Net built successfully...\n")
print("Starting training...\n")

# Initialize variables
# init_all = tf.initialize_all_variables()
init_all = tf.global_variables_initializer()

# Launch session
sess = tf.Session()
sess.run(init_all)

# Training loop
for epoch in range(TRAINING_EPOCHS):
    # avg_cost = 0.
    # total_batch = int(training_input.shape[0] / BATCH_SIZE)
    # # Loop over all batches
    # for i in range(total_batch):
    #     randidx = np.random.randint(len(training_input), size=BATCH_SIZE)
    #     batch_xs = training_input[randidx, :]
    #     # print(repr(batch_xs))
    #     batch_ys = training_target[randidx, :]
    #     # print(repr(batch_ys))
    #
    #     # Fit using batched data
    #     sess.run(optimizer, feed_dict={X: batch_xs, y: batch_ys, dropout_keep_prob: 0.9})
    #
    #     # Calculate average cost
    #     avg_cost += sess.run(accuracy, feed_dict={X: batch_xs, y: batch_ys, dropout_keep_prob: 1.}) / total_batch
    _, c = sess.run([optimizer, cost], feed_dict={X: training_input, y: training_target, dropout_keep_prob: 0.9})
    # print(c)

    # Display progress
    if epoch % DISPLAY_STEP == 0:
        # print("Epoch: %03d/%03d cost: %.9f" % (epoch, TRAINING_EPOCHS, avg_cost))
        print("Epoch: %03d/%03d" % (epoch, TRAINING_EPOCHS))
        # train_acc = sess.run(accuracy, feed_dict={X: batch_xs, y: batch_ys, dropout_keep_prob: 1.})
        train_acc = sess.run(accuracy, feed_dict={X: training_input, y: training_target, dropout_keep_prob: 1.})
        print("Training accuracy: %.6f" % train_acc)

print("End of training.\n")
print("Testing...\n")

# Testing
test_acc = sess.run(pred, feed_dict={X: testing_input, y: testing_target, dropout_keep_prob: 1.})
# print("Test accuracy: %.6f" % test_acc)
print(repr(np.column_stack((test_acc, testing_target))))
for i in np.column_stack((test_acc, testing_target)):
    print(repr(i))
sess.close()
print("Session closed!")

# Plotting
t = np.arange(raw_data[testing_data_idx_start][0], raw_data[testing_data_idx_start + N_TESTING_DATA][0],
              1.0 / TIME_INTERVAL)
plt.plot(t, testing_target, "grey")
plt.plot(t, test_acc, "red")
plt.xlabel('time')
plt.ylabel('non-linear heave')  # grayscale color
# plt.title('About as silly as it gets, folks', color='#afeeee')
plt.show()

