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
```java
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
![图片](https://github.com/yufeilong92/sqlcipher/blob/master/gif/gifa.gif)
* 滚动类
```java
    tvswt.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return new TextView(Main5Activity.this);
            }
        });
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("循环说据" + i);
        }
        TextSwitCherAnimation switCherAnimation = new TextSwitCherAnimation(tvswt, list);
        switCherAnimation.setDelayTime(500);
        switCherAnimation.create();
```
```java
public class TextSwitCherAnimation {

    private AnimationSet InAnimationSet;
    private AnimationSet OutAnimationSet;
    private static final int DURATION = 1000;
    private int marker;
    private TextSwitcher mTvSwc;
    private List<String> mList = new ArrayList<>();
    private int delayTime = 2000;
    private Handler handler = new Handler();
    private Runnable task = new Runnable() {
        @Override
        public void run() {
            nextView();
            handler.postDelayed(task, delayTime * 2);
        }
    };

    public TextSwitCherAnimation(TextSwitcher mTvSwc, List<String> mList) {
        this.mTvSwc = mTvSwc;
        this.mList = mList;
    }

    public void start() {
        handler.postDelayed(task, delayTime);
    }

    public void stop() {
        handler.removeCallbacks(task);
    }

    public int getMarker() {
        return marker;
    }

    public TextSwitCherAnimation setText(List<String> mList) {
        this.mList = mList;
        return this;
    }

    public void setDelayTime(int dalayTime) {
        this.delayTime = dalayTime;
    }

    public void create() {
        marker = 0;
        if (mList == null) {
            return;
        }
        if (mTvSwc == null) {
            return;
        }
        mTvSwc.setText(mList.get(0));
        createAnimation();
        mTvSwc.setInAnimation(InAnimationSet);
        mTvSwc.setOutAnimation(OutAnimationSet);
        start();

    }

    private void createAnimation() {
        AlphaAnimation alphaAnimation;
        TranslateAnimation translateAnimation;

        int h = mTvSwc.getHeight();
        if (h <= 0) {
            mTvSwc.measure(0, 0);
            h = mTvSwc.getMeasuredHeight();
        }

        InAnimationSet = new AnimationSet(true);
        OutAnimationSet = new AnimationSet(true);

        alphaAnimation = new AlphaAnimation(0, 1);
        translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, h, Animation.ABSOLUTE, 0);
        InAnimationSet.addAnimation(alphaAnimation);
        InAnimationSet.addAnimation(translateAnimation);
        InAnimationSet.setDuration(DURATION);

        alphaAnimation = new AlphaAnimation(1, 0);
        translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0, Animation.ABSOLUTE, -h);
        OutAnimationSet.addAnimation(alphaAnimation);
        OutAnimationSet.addAnimation(translateAnimation);
        OutAnimationSet.setDuration(DURATION);
    }

    private void nextView() {
        marker = ++marker % mList.size();
        mTvSwc.setText(mList.get(marker));
    }
}


```