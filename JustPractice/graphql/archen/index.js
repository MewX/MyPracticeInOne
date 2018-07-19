const express = require('express');
const graphqlHTTP = require('express-graphql');
const {Archen} = require('archen');

const options = {
    database: {
        dialect: 'mysql',
        connection: {
            host: 'localhost',
            user: 'root',
            password: 'root',
            database: 'graduation_project',
            timezone: 'Z',
            connectionLimit: 10
        }
    },
    graphql: {
        getAccessor: context => context.loader
    }
};

const archen = new Archen(options);

const app = express();
app.get('/', (req, res) => res.send('Hello World!'));

app.use(function (req, res, next) {
    req.loader = archen.getAccessor();
    next();
});

app.use(
    '/graphql',
    graphqlHTTP((request, response, params) => ({
        schema: archen.graphql.getSchema(),
        rootValue: archen.graphql.getRootValue(),
        pretty: false,
        graphiql: true,
        formatError: error => ({
            message: error.message,
            locations: error.locations,
            stack: error.stack ? error.stack.split('\n') : [],
            path: error.path
        })
    }))
);

// GraphiQL: http://127.0.0.1:3000/graphql
archen.getSchemaInfo().then(() => {
    app.listen(3000);
});
