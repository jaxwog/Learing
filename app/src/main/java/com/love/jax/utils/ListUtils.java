package com.love.jax.utils;

import com.love.jax.bean.LettersEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * com.love.jax.utils
 * Created by jax on 2018/11/13 17:28
 * TODO: 判断list是否为空
 */
public class ListUtils {
    public ListUtils() {
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() <= 0;
    }


    public static List<LettersEntity> addLetter(List<String> list){
        if (isEmpty(list)){
            return null;
        }
        List<LettersEntity> entityList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            LettersEntity lettersEntity = new LettersEntity();
            lettersEntity.setTitle(list.get(i).toString());
            lettersEntity.setTitleLetter(CharacterParser.getInstance().getSelling(list.get(i).toString()));
            Logger.d("wog","--------------------------------------------");
            Logger.d("wog","LettersEntity数据title="+lettersEntity.getTitle());
            Logger.d("wog","LettersEntity数据titleLetter="+lettersEntity.getTitleLetter());
            entityList.add(lettersEntity);
        }
        return entityList;
    }

}
