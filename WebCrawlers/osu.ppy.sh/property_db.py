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


    def does_record_exist(self, key):
        return self.get_value(key) is not None

    def save_or_overwrite_data(self, key, value):
        self.conn.execute("INSERT OR REPLACE INTO properties(name, value, time) VALUES (?, ?, current_timestamp);", (key, value,))


    def get_value(self, key):
        self.conn.execute("SELECT * FROM properties WHERE name = ?;", (key,))
        return self.conn.fetchone()
