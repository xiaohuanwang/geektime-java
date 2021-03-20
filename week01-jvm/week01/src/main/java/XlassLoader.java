import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @program: week01
 * @description:
 * @author: 王小欢
 * @create: 2021-03-20 21:14
 **/
public class XlassLoader extends ClassLoader{

    public static void main(String[] args) throws Exception {
        String path="/Users/wangxiaohuan/workspace/IdeaProjects/week01/src/main/resources/Hello.xlass";
        XlassLoader xlassLoader = new XlassLoader();
        Class loadClass = xlassLoader.loadClass(path);
       // 生成实例，调用方法
        Object instance = loadClass.newInstance();
        Method method = loadClass.getMethod("hello");
        method.invoke(instance);
    }

    /**
     * @description:从写findClass方法
     * @author wangxiaohuan
     * @Date 21:39 下午 2021/3/20
       No such property: code for class: Script1
     * @return 
     */
    @Override
    public Class findClass(String name){
        try {
            //从文件中读取转化成字节流
            File file = new File(name);
            FileInputStream inputStream = new FileInputStream(file);
            int length = inputStream.available();
            byte[] bytes = new byte[length];
            inputStream.read(bytes);
            //进行字节还原处理
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) (255 - bytes[i]);
            }
            //defineClass进行加载
            return defineClass("Hello", bytes, 0, bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
