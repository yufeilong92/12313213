# sqlcipher 加密
```
  SQLiteDatabase database = mHelp.getWritableDatabase(DbHelp.PASSWORD);
                ContentValues values = new ContentValues();
                values.put("name", "小明");
                values.put("age", 2);
                values.put("sex", 1);
                values.put("phone", "18317837561");
                database.insert(DbHelp.DBTABLENAMEONE, null, values);

  SQLiteDatabase database1 = mHelp.getReadableDatabase(DbHelp.PASSWORD);
                  Cursor query = database1.query(DbHelp.DBTABLENAMEONE, null, null, null, null,
                                        null, null);
                   StringBuffer buffer = new StringBuffer();
                   while (query.moveToNext()) {
                                    String name = getString(query, "name");
                                    int age = getInteger(query, "age");
                                    int sex = getInteger(query, "sex");
                                    String phone = getString(query, "phone");
                                    buffer.append(name);
                                    buffer.append(age);
                                    buffer.append(sex);
                                    buffer.append(phone);
                                }
                   query.close();
    Cursor query1 = database1.query(DbHelp.DBTABLENAMEONE, null, null, null, null,
                                        null, null);
                    StringBuffer buffer1 = new StringBuffer();
                     while (query1.moveToNext()) {
                                    String name = getString(query1, "name");
                                    int age = getInteger(query1, "age");
                                    int sex = getInteger(query1, "sex");
                                    String phone = getString(query1, "phone");
                                    buffer1.append(name);
                                    buffer1.append(age);
                                    buffer1.append(sex);
                                    buffer1.append(phone);
                     }
```
