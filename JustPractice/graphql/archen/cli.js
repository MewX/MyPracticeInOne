const {graphql} = require('graphql');
const {Archen} = require('archen');

const archen = new Archen({
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
    // graphql: {
    //     getAccessor: context => context.accessor
    // }
});

archen.getSchemaInfo().then(() => {
    const [schema, rootValue, accessor] = [
        archen.graphql.getSchema(),
        archen.graphql.getRootValue(),
        archen.getAccessor()
    ];

    const query1 = `
    {
      task(where: {id: 1001}) {
        name,
        content,
        type,
        holder {
          name
        }
      }
    }`;
    const query2 = `
    {
      tasks {
        id,
        name,
        content,
        type,
        holder {
          name
        }
      }
    }`;

    graphql(schema, query1, rootValue, {accessor}).then(response => {
        console.log(JSON.stringify(response, null, 2));
    });
});

