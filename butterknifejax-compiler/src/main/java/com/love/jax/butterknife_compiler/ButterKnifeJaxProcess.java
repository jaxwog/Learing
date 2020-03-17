package com.love.jax.butterknife_compiler;


import com.google.auto.service.AutoService;
import com.love.jax.butterknife_annotations.BindViewJax;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;

/**
 * com.love.jax.butterknife_compiler
 * Created by jax on 2020-01-19 10:48
 * TODO:相当于一个监听者，盯着app中的注解
 * 需要让我们的类拥有注解处理器能力
 */


 @AutoService(Processor.class)
public class ButterKnifeJaxProcess extends AbstractProcessor {

     //1.确认我们处理哪些注解
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(BindViewJax.class.getCanonicalName());
        return types;
    }

    //2.JDK支持的最高版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    //3.定义一个用来生成java文件的对象
    Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
    }

    /**
     * 按照自己的要求对注解进行处理
     * @param set
     * @param roundEnvironment
     *package com.love.jax.bean;                   //PackageElement
     *public class CityBean {                       //TypeElement
     *     private String city;                     //VariableElement
     *     private boolean isTop;                   //VariableElement
     *     public CityBean() { }                   //ExecuteableElement
     *     public String getCity() { return city; } //ExecuteableElement
     * }
     *
     *
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
       Set<? extends Element> elementSet  = roundEnvironment.getElementsAnnotatedWith(BindViewJax.class);

         //需要对不同的Activity中用于的注解进行分类操作
        Map<String, List<VariableElement>> cacheMap = new HashMap<>();

        //对注解进行分类（同一个文件放到一块）
        for (Element element:elementSet){
            VariableElement variableElement = (VariableElement) element;
            //获取Activity的全类名，com.love.jax.activity.design.ButterKnifeActivity
            String acitvityName = getActivityName(variableElement);
            List<VariableElement> list = cacheMap.get(acitvityName);
            if (list==null){
                list = new ArrayList<>();
                cacheMap.put(acitvityName,list);
            }
            list.add(variableElement);
        }

        //开始产生java文件
        Iterator iterator = cacheMap.keySet().iterator();
        while (iterator.hasNext()){
            //1.获取Activity的名字
            String activity = (String) iterator.next();
            //2.获取Activity中所有成员
            List<VariableElement> cacheElements = cacheMap.get(activity);
            //3.获取  包名
            String packageName = getPackageName(cacheElements.get(0));
            //4.获取最后生成的文件的文件名 com.love.jax.activity.design.ButterKnifeActivity$ViewBinderJax
            String newAcitvityBinder = activity+"$ViewBinderJax";

            //生成额外文件
            /**
             * package  com.love.jax.activity.design;
             * import  com.love.jax.activity.design.ViewBinderJax;
             *     public class  ButterKnifeActivity$ViewBinderJax implements ViewBinderJax<com.love.jax.activity.design.ButterKnifeActivity> {
             *          public void bind(com.love.jax.activity.design.ButterKnifeActivity target){
             *                  target.mButton = (android.widget.Button)target.findViewById(2131296321);
             *          }
             * }
             */


            Writer writer = null;
            try {
                JavaFileObject javaFileObject =  mFiler.createSourceFile(newAcitvityBinder);
                writer = javaFileObject.openWriter();
                //ButterKnifeActivity$ViewBinderJax
                String activitySimpleName = cacheElements.get(0).getEnclosingElement().getSimpleName().toString()+"$ViewBinderJax";
                writer.write("package  "+packageName+";");
                writer.write("\n");
                writer.write("import  "+packageName+".ViewBinderJax;");
                writer.write("\n");
                writer.write("public class  "+activitySimpleName+" implements ViewBinderJax<"+activity+"> {");
                writer.write("\n");
                writer.write("public void bind("+activity+" target){");
                writer.write("\n");

                for (VariableElement variableElement:cacheElements){
                    BindViewJax bindViewJax = variableElement.getAnnotation(BindViewJax.class);
                    String filedName = variableElement.getSimpleName().toString();
                    TypeMirror typeMirror = variableElement.asType();
                    writer.write("target."+filedName+" = ("+typeMirror.toString()+")"+"target.findViewById("+bindViewJax.value()+");");
                    writer.write("\n");
                }

                writer.write("}");
                writer.write("\n");
                writer.write("}");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        return false;
    }

    private String getPackageName(VariableElement variableElement) {
        TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
        String packageName = processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString();

        return packageName;
    }

    private String getActivityName(VariableElement variableElement) {
        String packageName = getPackageName(variableElement);
        TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();

        return packageName+"."+typeElement.getSimpleName().toString();
    }
}
