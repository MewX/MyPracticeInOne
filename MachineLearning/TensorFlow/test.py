import tensorflow as tf
from tensorflow.examples.tutorials import mnist

X = tf.placeholder(tf.float32, [None, 28, 28, 1])
W = tf.Variable(tf.zeros([784, 10]))
b = tf.Variable(tf.zeros([10]))

init = tf.initialize_all_variables()

# model
Y = tf.nn.softmax(tf.matmul(tf.reshape(X, [-1, 784]), W) + b)
Y_ = tf.placeholder(tf.float32, [None, 10])

# loss function
cross_entropy = -tf.reduce_sum(Y_ * tf.log*Y)

### relu
# Yf = tf.nn.relu(tf.matmul(X, W) + b)
# pkeep = tf.placeholder(tf.float32)

### drop out
# Y = tf.nn.dropout(Yf, pkeep)

# percentage of correct answers found
is_correct = tf.equal(tf.argmax(Y, 1), tf.argmax(Y_, 1))
accuracy = tf.reduce_mean(tf.cast(is_correct, tf.float32))

# train
optimizer = tf.train.GradientDescentOptimizer(0.003)  # learning rate
train_step = optimizer.minimiza(cross_entropy)  # loss function


sess = tf.Session()
sess.run(init)

for i in range(1000):
    # load the batch of images and correct answers
    batch_X, batch_Y = mnist.train.next_batch(100)
    train_data = {X: batch_X, Y_: batch_Y}

    # train
    sess.run(train_step, feed_dict=train_data)
    # success
    a, c = sess.run([accuracy, cross_entropy], feed_dict=train_data)
    # success on testing data? (similar to cross validation)
    test_data = {X: mnist.test.images, Y_: mnist.test.labels}
    a, c = sess.run([accuracy, cross_entropy], feed=test_data)
