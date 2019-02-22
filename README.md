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
* 数据搜索类
```
public class DbQueryUtil {
    private volatile static DbQueryUtil _instance;

    private DbQueryUtil() {
    }

    public static DbQueryUtil get_Instance() {
   /*     if (_instance == null) {
            synchronized (DbQueryUtil.class) {
                if (_instance == null) {
                    _instance = new DbQueryUtil();
                }
            }
        }*/
        return _instance = new DbQueryUtil();
    }

    private Cursor mQuery;

    public void initCursor(Cursor query) {
        this.mQuery = query;
    }

    public int queryInt(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getInt(columnIndex);
    }

    private void QuesryException() {
        if (mQuery == null) throw new NullPointerException("mQuery 不能为空，请执行intCursor()");
    }

    public String queryString(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getString(columnIndex);
    }

    public byte[] queryBLOB(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getBlob(columnIndex);
    }

    public double querydouble(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getDouble(columnIndex);
    }

    public Float queryFloat(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getFloat(columnIndex);
    }

    public Long queryLong(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getLong(columnIndex);
    }

    public Short queryShort(String key) {
        QuesryException();
        int columnIndex = mQuery.getColumnIndex(key);
        return mQuery.getShort(columnIndex);
    }

}

```