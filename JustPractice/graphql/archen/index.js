const express = require('express');
const graphqlHTTP = require('express-graphql');

const app = express();

const knex = require('knex')({
  client: 'mysql,
  host: '127.0.0.1',
  user: 'root',
  password: 'root',
  database: 'graduation_project'
});

// here requires generating the schema.json
// i am using the tool: https://github.com/rexxars/sql-to-graphql

const archen = require('archen')(
  require('fs').readFileSync('data/schema.json')
);


