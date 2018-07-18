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
    }
});

archen.getSchemaInfo().then(() => {
    const [schema, rootValue, accessor] = [
        archen.graphql.getSchema(),
        archen.graphql.getRootValue(),
        archen.getAccessor()
    ];

    // const query = `
    // {
    //   task(id:"1001") {
    //     name
    //     content
    //     holder {
    //       name
    //     }
    //     type
    //   }
    // }`;
    const query = `
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

    graphql(schema, query, rootValue, {accessor}).then(response => {
        console.log(JSON.stringify(response));
    });
});

