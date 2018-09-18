package com.example.administrator.greendaotest.db;


import android.app.Application;

import com.example.administrator.greendaotest.App;
import com.example.administrator.greendaotest.Student;
import com.example.administrator.greendaotest.dao.StudentDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 完成对某一张数据表的具体操作，ORM操作
 * Created by xiawenquan on 17/12/19.
 */

public class GreenDaoUtils {

    private static GreenDaoUtils instance;

    private ExecutorService cachedThreadPool;

    public static GreenDaoUtils getInstance() {
        if (instance == null) {
            DaoManager.getInstance().init(App.getInstance());
            DaoManager.getInstance().setDebug();
            instance = new GreenDaoUtils();

        }

        return instance;
    }

    public ExecutorService getCachedThreadPool() {
        if (cachedThreadPool == null) {
            cachedThreadPool = Executors.newScheduledThreadPool(10);
        }
        return cachedThreadPool;
    }

    public void getBaseUser(final long userId, final ExecuteCallBack callBack) {
        getCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                QueryBuilder<Student> queryBuilder = DaoManager.getInstance().getDaoSession().queryBuilder(Student.class);
                List<Student> list = queryBuilder.where(StudentDao.Properties.Id.eq(userId)).list();
                if (callBack != null) {
                    callBack.callBack(list.get(0));
                }
            }
        });
    }

    public Student getBaseUser(final long userId) {
        QueryBuilder<Student> queryBuilder = DaoManager.getInstance().getDaoSession().queryBuilder(Student.class);
        return queryBuilder.where(StudentDao.Properties.Id.eq(userId)).list().get(0);
    }

    public void getListBaseUser(final String sql, final String[] conditions, final ExecuteCallBack callBack) {
        getCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                List<Student> list = DaoManager.getInstance().getDaoSession().queryRaw(Student.class, sql, conditions);
                if (callBack != null) {
                    callBack.callBack(list);
                }
            }
        });
    }


    /**
     * 单条插入数据
     *
     * @param baseUser
     * @return
     */
    public void insertBaseUser(final Student baseUser) {
        getCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                DaoManager.getInstance().getDaoSession().getStudentDao().insertOrReplace(baseUser);
            }
        });
    }

    /**
     * 批量插入数据
     *
     * @param list
     */
    public void insertListBaseUser(final List<Student> list) {
        getCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                DaoManager.getInstance().getDaoSession().getStudentDao().insertOrReplaceInTx(list);
            }
        });
    }





    /**
     * 单条删除数据
     *
     * @param baseUser
     */
    public void deleteBaseUser(final Student baseUser) {
        getCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                DaoManager.getInstance().getDaoSession().getStudentDao().delete(baseUser);
            }
        });
    }

    /**
     * 批量删除数据
     *
     * @param list
     */
    public void deleteListBaseUser(final List<Student> list) {
        getCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                DaoManager.getInstance().getDaoSession().getStudentDao().deleteInTx(list);
            }
        });
    }

    /**
     * 清除baseUser数据表
     */
    public void deleteAllBaseUser() {
        getCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                DaoManager.getInstance().getDaoSession().getStudentDao().deleteAll();
            }
        });
    }


    /**
     * 清除指定的表数据
     *
     * @param entityClass
     */
    public <T> void deleteAll(final Class<T> entityClass) {
        getCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                DaoManager.getInstance().getDaoSession().deleteAll(entityClass);
            }
        });
    }


    /**
     * 批量清表数据
     *
     * @param entityClass
     * @param <T>
     */
    public <T> void deleteAll(final List<Class<T>> entityClass) {
        getCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < entityClass.size(); i++) {
                    DaoManager.getInstance().getDaoSession().deleteAll(entityClass.get(i));
                }

                if (entityClass != null) {
                    entityClass.clear();
                }
            }
        });
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public static List<Student> queryAll() {
        QueryBuilder<Student> builder = DaoManager.getInstance().getDaoSession().getStudentDao().queryBuilder();

        return builder.build().list();
    }



    public void onDestroy() {
        if (cachedThreadPool != null) {
            cachedThreadPool.shutdownNow();
        }
        cachedThreadPool = null;

        DaoManager.getInstance().closeConnection();

        if (instance != null) {
            instance = null;
        }

    }

    public interface ExecuteCallBack {
        void callBack(Object object);
    }

}
