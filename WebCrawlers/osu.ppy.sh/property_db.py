# the file for handling the property db
import sqlite3

import settings

"""
the property-like db
"""
class PropertyDb:

    def __init__(self):
        self.conn = sqlite3.connect(settings.DB_NAME)
        self.init_db()


    def init_db(self):
        self.conn.execute('CREATE TABLE IF NOT EXISTS properties (name TEXT PRIMARY KEY, value TEXT, time DATETIME DEFAULT current_timestamp);')


    def close(self):
        self.conn.close()


    def does_record_exist(self, key):
        return self.get_value(key) is not None

    def save_or_overwrite_data(self, key, value):
        self.conn.execute("INSERT OR REPLACE INTO properties(name, value, time) VALUES (?, ?, current_timestamp);", (key, value,))
        self.conn.commit()


    def get_value(self, key):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM properties WHERE name = ?;", (key,))
        return cur.fetchone()
