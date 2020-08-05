package com.love.jax.activity.design;



import android.view.View;
import android.widget.TextView;

import com.love.jax.R;
import com.love.jax.activity.BaseActivity;
import com.love.jax.fastjson.JSON;
import com.love.jax.json.FastJson;
import com.love.jax.json.News;
import com.love.jax.json.User;
import com.love.jax.utils.Logger;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * json字符串解析
 */
public class FastJsonActivity extends BaseActivity {

    private TextView mTextView;

    @Override
    protected void initJestListener() {

    }

    @Override
    protected void initView() {
        mTextView = findViewById(R.id.content);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_fast_json;
    }

    public void json(View view) {
        News news = new News();
        news.setId(1);
        news.setTitle("新年放假通知");
        news.setContent("从今天开始放假啦。");
        news.setAuthor(createAuthor());
        news.setReader(createReaders());
        List<News> list = new ArrayList<>();
        list.add(0,news);
        list.add(1,news);
        Logger.i("jax",FastJson.toJson(list));

        Logger.i("jax",JSON.toJSONString(list));

        mTextView.setText(FastJson.toJson(list));
    }

    public void object2(View view) {
        News news = new News();
        news.setId(1);
        news.setTitle("新年放假通知");
        news.setContent("从今天开始放假啦。");
        news.setAuthor(createAuthor());
        news.setReader(createReaders());
        String json = FastJson.toJson(news);
        Logger.i("jax",json);
         Logger.i("jax","json转对象："+JSON.parseObject(json,News.class).toString());

        mTextView.setText(((News)FastJson.pareseObject(json,News.class)).toString());
    }





    private static List<User> createReaders() {
        List<User> readers = new ArrayList<User>();
        User readerA = new User();
        readerA.setId(2);
        readerA.setName("Jack");
        readers.add(readerA);

        User readerB = new User();
        readerB.setId(1);
        readerB.setName("Lucy");
        readers.add(readerB);

        return readers;
    }

    private static User createAuthor() {
        User author = new User();
        author.setId(1);
        author.setName("Fancyy");
        author.setPwd("123456");
        return author;
    }
}
