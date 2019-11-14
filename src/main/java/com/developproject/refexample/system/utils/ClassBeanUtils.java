/*
 * 版    权:  Timesoft Copyright 2014-2016,All rights reserved
 * 文 件 名:  ClassBeanUtils.java
 * 描       述:  ClassBeanUtils
 * 修改人:  罗雄伟
 * 修改时间:  2012-2-8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.developproject.refexample.system.utils;


import com.developproject.refexample.system.action.BaseAction;
import com.developproject.refexample.system.dto.BasDto;
import org.apache.commons.lang.ClassUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.function.Function;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * ClassBean单元
 * 
 * @author 罗雄伟
 * @version [版本号, 2012-2-8]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ClassBeanUtils {

	private static ApplicationContext _ApplicationContext;
	
	/**
	 * 取得实例Class的实现接口名
	 * 
	 * @param clazz
	 *            实例Class
	 * @return String
	 */
	public static String getClassInterfaceName(Class<?> clazz) {
		Class<?> interfaceClass = getClassInterface(clazz);
		return interfaceClass.getName();
	}

	/**
	 * 取得实例Class的实现接口
	 * 
	 * @param clazz
	 *            实例Class
	 * @return Class<?>
	 */
	public static Class<?> getClassInterface(Class<?> clazz) {
		List<?> classs = ClassUtils.getAllInterfaces(clazz);
		return (Class<?>) classs.get(0);
	}

	/**
	 * 取得实例Class的范型参数名
	 * 
	 * @param clazz
	 *            实例Class
	 * @return String
	 */
	public static String getBeanPropertyTypeName(Class<?> clazz) {
		Class<?> typeClass = getBeanPropertyType(clazz);
		return typeClass.getName();
	}

	/**
	 * 取得实例Class的范型参数
	 * 
	 * @param clazz
	 *            实例Class
	 * @return Class<?>
	 */
	public static Class<?> getBeanPropertyType(Class<?> clazz) {
		return getBeanPropertyType(clazz, 1);
	}

	/**
	 * 取得实例Class的范型参数
	 * 
	 * @param clazz
	 *            实例Class
	 * @param index
	 *            索引
	 * @return Class<?>
	 */
	public static Class<?> getBeanPropertyType(Class<?> clazz, int index) {
		Class<?>[] classs = getBeanPropertyTypes(clazz);
		return classs[index];
	}

	/**
	 * 取得实例Class的范型参数列表
	 * 
	 * @param clazz 实例Class
	 * @return Class<?>[]
	 */
	public static Class<?>[] getBeanPropertyTypes(Class<?> clazz) {
		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return null;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (params != null && params.length > 0) {
			List<?> list = Arrays.asList(params);
			return list.toArray(new Class<?>[] {});
		} else {
			return new Class<?>[] {};
		}
	}

	/**
	 * 取得实例Class的父类Class
	 * 
	 * @param clazz
	 *            实例Class
	 * @return Class<?>
	 */
	public static Class<?> getSuperClass(Class<?> clazz) {
		return clazz.getSuperclass();
	}

	/**
	 * 取得实例Class的实例化对象
	 * @param <T>
	 * @param clazz 实例Class
	 * @return T
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T getInstantiateObject(Class<?> clazz) throws SecurityException, NoSuchMethodException{
		return getInstantiateObject(clazz,new Object[]{});
	}
	
	/**
	 * 取得实例Class的实例化对象
	 * 
	 * @param <T>
	 * @param clazz 实例Class
	 * @return T
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws ClassNotFoundException 
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T getInstantiateObject(String clazzpath,Object... parameters) throws SecurityException, NoSuchMethodException, ClassNotFoundException {
		Class<?> clazz = Class.forName(clazzpath);
		return getInstantiateObject(clazz, parameters);
	}
	
	/**
	 * 取得实例Class的实例化对象
	 * 
	 * @param <T>
	 * @param clazz 实例Class
	 * @return T
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getInstantiateObject(Class<?> clazz,Object... parameters) throws SecurityException, NoSuchMethodException {
		Class<?>[] parameterTypes = SystemCommonF.setNullObj();
		if (null==parameters || parameters.length == 0){
			parameterTypes = new Class<?>[]{};
		}else{
			List<Class<?>> list = new ArrayList<Class<?>>();
			for (Object obj : parameters){
				list.add(obj.getClass());
			}	
			parameterTypes = list.toArray(new Class<?>[]{});
		}
		Constructor<T> constructor = (Constructor<T>)clazz.getConstructor(parameterTypes);
		T instantiateClass = (T) BeanUtils.instantiateClass(constructor, parameters);
		return instantiateClass;
	}

	/**
	 * 取得实例Class的实例化对象
	 * 
	 * @param <T>
	 * @param clazzpath 实例Class路径
	 * @return T
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T getInstantiateObject(String clazzpath)
			throws ClassNotFoundException, SecurityException, NoSuchMethodException {
		Class<?> clazz = Class.forName(clazzpath);
		return getInstantiateObject(clazz); 
	}

	/**
	 * 根据Bean名称取得bean实例
	 * 
	 * @param <T>
	 * @param bean
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String bean) {
		getApplicationContext();
		return (T)getBean(bean,_ApplicationContext);
	}
	
	/**
	 * 根据Bean名称取得bean实例
	 * 
	 * @param <T>
	 * @param bean
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T getBean2(String bean) {
		return getBean2(bean,new Object[] {});
	}
	
	/**
	 * 根据Bean名称取得bean实例
	* @param bean
	* @param parameters
	* @return T    返回类型 
	* @see
	 */
	public static <T> T getBean2(String bean,Object... parameters) {
		getApplicationContext();
		Object object = SystemCommonF.setNullObj();
		try {
			object = getBean(bean,_ApplicationContext);
		} catch (Exception e) {
			//SystemCommonF.errorOutToLogger("1000", "创建对象发生错误！", e);
		}
		try {
			if (null == object) {
				Class<?> clazz = Class.forName(bean);
				if (SystemCommonF.isEmpty(parameters)) {
					object = clazz.newInstance();
				}else {
					object = getInstantiateObject(clazz, parameters);
				}
			}
			return (T)object;
		} catch (final Exception e) {
			throw new BusinessException("1000", "创建对象发生错误！", e);
		}
	}
	
	/**
	 * 根据Bean名称取得bean实例
	 * @param <T>
	 * @param bean
	 * @param application
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String bean, ApplicationContext application) {
		return (T)application.getBean(bean);
	}
	
	/**
	 * 根据Bean名称取得bean实例
	 * @param <T>
	 * @param bean
	 * @param application
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> clazz, ApplicationContext application) {
		return (T)getBean(clazz.getName(), application);
	}
	
	/**
	 * 获取ApplicationContext
	 * @return ApplicationContext
	 * @see [类、类#方法、类#成员]
	 */
	public static ApplicationContext getApplicationContext(){
		if (null ==_ApplicationContext){
			_ApplicationContext = Constants.webApplicationContext;//WebApplicationContextUtils.getRequiredWebApplicationContext(ServletActionContext.getServletContext());
		}		
		return _ApplicationContext;
	}
	
	/**
	 * 获取ApplicationContext
	 * @param filepaths
	 * @return ApplicationContext
	 * @see [类、类#方法、类#成员]
	 */
	public static ApplicationContext getApplicationContext(String... filepaths){
		_ApplicationContext = new ClassPathXmlApplicationContext(filepaths);
		return _ApplicationContext;
	}

	/**
	 * 根据Class取得bean实例
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <T> T getBean(Class<?> clazz) {
		return getBean(clazz.getName());
	}

	/**
	 * 取得字段
	 * @param clazz
	 * @param fieldName
	 * @return java.lang.reflect.Field
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @see [类、类#方法、类#成员]
	 */
	public static Field getField(Class<?> clazz,String fieldName) throws SecurityException, NoSuchFieldException{
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (Exception e) {
			return clazz.getSuperclass().getDeclaredField(fieldName);
		} 
	}
	
	/**
	 * 取得get方法
	 * @param clazz
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws NoSuchMethodException
	 * @see [类、类#方法、类#成员]
	 */
	public static Method getGetMethod(Class<?> clazz,String fieldName) throws SecurityException, NoSuchFieldException, NoSuchMethodException{
		Field field = getField(clazz,fieldName);
		return getGetMethod(field);
	}
	
	/**
	 * 取得get方法
	 * @param field
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @see [类、类#方法、类#成员]
	 */
	public static Method getGetMethod(Field field) throws SecurityException, NoSuchMethodException{
		String methodName;

		String getbz = "get";
		
		if (field.getType().equals(boolean.class)) {
			getbz = "is";
		}
		
		String key = field.getName();
		
		methodName = getbz + key.substring(0, 1).toUpperCase() + key.substring(1, key.length());
	 	return field.getDeclaringClass().getMethod(methodName, new Class[] {});
	}
	
	/**
	 * 取得get方法
	 * @param clazz
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws NoSuchMethodException
	 * @see [类、类#方法、类#成员]
	 */
	public static Method getSetMethod(Class<?> clazz,String fieldName) throws SecurityException, NoSuchFieldException, NoSuchMethodException{
		Field field = getField(clazz,fieldName);
		return getSetMethod(field);
	}
	
	/**
	 * 检查字段是否存在
	 * @param clazz
	 * @param fieldName
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean containsField(Class<?> clazz,String fieldName){
		boolean result = false;
		try{
			Method f = ClassBeanUtils.getGetMethod(clazz, fieldName);
			if (null !=f){
				result = true;
			}
		}catch(Exception e){
			result = false; 
		}
		return result;
	}
	
	/**
	 * 取得get方法
	 * @param field
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @see [类、类#方法、类#成员]
	 */
	public static Method getSetMethod(Field field) throws SecurityException, NoSuchMethodException{
		String methodName;

		String getbz = "set";
		
		String key = field.getName();
		
		methodName = getbz + key.substring(0, 1).toUpperCase() + key.substring(1, key.length());
	 	return field.getDeclaringClass().getMethod(methodName, field.getType());
	}
	
	/**
	 * 通过反射对象get方法取对象中值
	 * @param obj
	 * @param key
	 * @return Object
	 * @throws NoSuchMethodException 
	 * @throws NoSuchFieldException 
	 * @throws SecurityException 
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @see [类、类#方法、类#成员]
	 */
	public static Object getAttributeValue(Object obj, String key) throws SecurityException, NoSuchFieldException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Object value = SystemCommonF.setNullObj();
		if (key.indexOf(".") > 0){
			key = key.replace(".", ",");
			String[] keys = key.split(",");
			 for (int i = 0; i < keys.length;i++ ){
				 if (i == 0)
                 {
                     value = getAttributeValue(obj,keys[i]);
                     if (value == null) return null;
                 }else{
                     if (i == keys.length -1)
                     {
                    	 value = getAttributeValue(value,keys[i]);
                     }
                     else
                     {
                    	 value = getAttributeValue(value,keys[i]);
                         if (value == null) return null;
                     }                	 
                 }
			 }
			 return value;
		}
		else
		{
			Method fMethod = getGetMethod(obj.getClass(), key);
			return fMethod.invoke(obj, new Object[] { });
		}
	}
	
	/**
	 * 通过反射对象get方法取对象中值
	 * @param obj
	 * @param key
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static <V> V getAttributeAnyValue(Object obj, String key){
		Object v = SystemCommonF.setNullObj();

		try {
			v = getAttributeValue(obj, key);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return (V)v;
	}

	
	/**
	 * 通过反射对象set方法对对象中赋值
	 * @param obj
	 * @param key
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @see [类、类#方法、类#成员]
	 */
	public static void setAttributeValue(Object obj, String key,Object value) throws SecurityException, NoSuchFieldException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Object value1  = SystemCommonF.setNullObj();
		
        if (key.indexOf(".") > 0)
        {
        	key = key.replace(".", ",");
            String[] keys = key.split(",");
            for (int i = 0; i < keys.length; i++)
            {
                if (i == 0)
                {
                	value1 = getAttributeValue(obj,keys[i]);
                    if (value1 == null) return;
                }
                else
                {
                    if (i == keys.length - 1)
                    {
                        setAttributeValue(value1,keys[i], value);
                        return;
                    }
                    else
                    {
                    	value1 = getAttributeValue(value1,keys[i]);
                        if (value1 == null) return;
                    }
                }
            }
        }

        Method fMethod = getSetMethod(obj.getClass(), key);
        if (fMethod != null)
        {
    		value1  = TypeCaseHelper.convert(value, fMethod.getParameterTypes()[0], ""); 
    		fMethod.invoke(obj, value1);
        }
	}
	
	/**
	 * 拷贝对象属性
	 * @param source 源对象
	 * @param target 目标对象
	 * @param properties 排除的属性列表
	 * @see [类、类#方法、类#成员]
	 */
	public static void copyIgnoreProperties(Object source, Object target, String ... properties){
		BeanUtils.copyProperties(source, target, properties);
	}
	
	/**
	 * 拷贝对象属性
	 * @param source 源对象
	 * @param target 目标对象
	 * @param properties 包含的属性列表
	 * @see [类、类#方法、类#成员]
	 */
	public static void copyIncludeProperties(Object source, Object target, String ... properties){
		//排除字段列表
		List<String> result = new ArrayList<String>();
		//包含字段列表
		List<String> propertiesList = Arrays.asList(properties);
		//源对象字段列表过滤出排除字段列表
		for (Field f : source.getClass().getDeclaredFields()){
			//当前字段在包含字段列表中不存在将当前字段添加到排除字段列表
			if (!propertiesList.contains(f.getName())){
				result.add(f.getName());
			}
		}
		//拷贝对象属性
		copyIgnoreProperties(source, target, result.toArray(new String[]{}));
	}
	
	/**
	 * 拷贝对象属性
	 * @param source 源对象
	 * @param target 目标对象
	 * @see [类、类#方法、类#成员]
	 */
	public static void copyProperties(Object source, Object target){
		BeanUtils.copyProperties(source, target);
	}
	


	/**
     * 以文件的形式来获取包下的所有Class
     * 
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    private static void findAndAddClassesInPackageByFile(String packageName,String packagePath, final boolean recursive, Set<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            // log.warn("用户定义包名 " + packageName + " 下没有任何文件");
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        // 循环所有文件
        for (File file : dirfiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive,classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0,file.getName().length() - 6);
				try {
                    // 添加到集合中去
                    //classes.add(Class.forName(packageName + '.' + className));
                    //经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净

					classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			}
        }
    }
 
    /**
     * 获取包中所有类
     * @param pack
     * @return
     * @throws NoSuchMethodException 
     * @throws SecurityException 
     * @see [类、类#方法、类#成员]
     */
	public static Set<Class<?>> getClasses(String pack) throws SecurityException, NoSuchMethodException{
	      // 第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字 并进行替换
        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    //System.err.println("file类型的扫描");
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath,recursive, classes);
                } else if ("jar".equals(protocol)) {
                    // 如果是jar包文件
                    // 定义一个JarFile
                    //System.err.println("jar类型的扫描");
                    JarFile jar;
                    try {
                        // 获取jar
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        // 从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        // 同样的进行循环迭代
                        while (entries.hasMoreElements()) {
                            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            // 如果是以/开头的
                            if (name.charAt(0) == '/') {
                                // 获取后面的字符串
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                // 如果以"/"结尾 是一个包
                                if (idx != -1) {
                                    // 获取包名 把"/"替换成"."
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                // 如果可以迭代下去 并且是一个包
                                if ((idx != -1) || recursive) {
                                    // 如果是一个.class文件 而且不是目录
                                    if (name.endsWith(".class") && !entry.isDirectory()) {
                                        // 去掉后面的".class" 获取真正的类名
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                                        try {
                                            // 添加到classes
                                            classes.add(Class.forName(packageName + '.' + className));
                                        } catch (ClassNotFoundException e) {
                                            // log
                                            // .error("添加用户自定义视图类错误 找不到此类的.class文件");
                                        	//SystemCommonF.errorOutToLogger("00060", "未获取到类文件实例!", e);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        // log.error("在扫描用户定义视图时从jar包获取文件出错");
                    	e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
			e.printStackTrace();
        }
        return classes;
	}
	
	/**
	 * 获取包中所有父类为指定类的Class
	 * @param pack
	 * @param superclass
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @see [类、类#方法、类#成员]
	 */
	public static Set<Class<?>> getClassesBySuperclass(String pack,Class<?> superclass) throws SecurityException, NoSuchMethodException{
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		Set<Class<?>> sets =  getClasses(pack);
		for (Class<?> v : sets){
			Class<?> superclazz = getSuperClass(v);
			if (null != superclazz && superclazz.equals(superclass)){
				classes.add(v);
			}
		}
		return classes;
	}
	
	/**
	 * 获取包中所有标注为指定类的标注类的Class
	 * @param pack
	 * @param auth
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @see [类、类#方法、类#成员]
	 */
	public static <T extends Annotation> Set<Class<?>> getClassesByAuth(String pack,Class<T> auth) throws SecurityException, NoSuchMethodException{
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		Set<Class<?>> sets =  getClasses(pack);
		for (Class<?> v : sets){
			Annotation a =  v.getAnnotation(auth);
			if (null != a){
				classes.add(v);
			}
		}
		return classes;
	}
	
	/**
	 * 获取包中所有标注为指定类的标注类的Method
	 * @param pack
	 * @param auth
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @see [类、类#方法、类#成员]
	 */
	public static <T extends Annotation> Set<Method> getMethodsByAuth(Class<?> clazz,Class<T> auth){
		Set<Method> methods = new LinkedHashSet<Method>();
		Method[] methods2 = clazz.getMethods();
		for (Method v : methods2){
			Annotation a =  v.getAnnotation(auth);
			if (null != a){
				methods.add(v);
			}
		}
		return methods;
	}
	
	/**
	 * 检查实例是否单例
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		getApplicationContext();
		return _ApplicationContext.isSingleton(name);
	}
	
	/**
	 * 检查实例是否单例
	 * @param clazz
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isSingleton(Class<?> clazz) throws NoSuchBeanDefinitionException {
		return isSingleton(clazz.getName());
	}

	/**
	 * 检查实例是否存在
	 * @param name
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean containsBean(String name) {
		getApplicationContext();
		return _ApplicationContext.containsBean(name);
	}
	
	/**
	 * 检查实例是否存在
	 * @param clazz
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean containsBean(Class<?> clazz) {
		return containsBean(clazz.getName());
	}
	
	/**
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws NoSuchBeanDefinitionException
	 */
	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		getApplicationContext();
	    return _ApplicationContext.getType(name);
	}
	  
	/**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名   
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		getApplicationContext();
		return _ApplicationContext.getAliases(name);
	}
	
	/**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名   
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
	public static String[] getAliases(Class<?> clazz) throws NoSuchBeanDefinitionException {
		getApplicationContext();
		return getAliases(clazz.getName());
	}
	
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		Set<Class<?>> classes = getClassesBySuperclass("com.timesoft", BaseAction.class);
		Set<Class<?>> classes1 = getClassesByAuth("com.timesoft", Controller.class);
		System.out.println(classes.toString());
		System.out.println(classes1.toString());
		BasDto bb = getBean2("com.timesoft.common.baseData.BasDto","a",1);
		System.out.println(classes1.toString());
	}

	/**
	 * 比较两个相同dto中指定属性的值是否相等(相同为true，不同为false)
	 * 目标
	 * 来源
	 * 属性列
	 * @param targetDto
	 * @param sourceDto
	 * @param attributes
	 * @return
	 */
	public static Boolean compareDto2Dto(Object targetDto,Object sourceDto,String [] attributes) throws Exception{
		List <String> attributesList=new ArrayList<String>();
		if(!SystemCommonF.isEmpty(attributes)){
			if(attributes.length>0){
				attributesList=Arrays.asList(attributes);
			}else{
				return false;
			}
		}else{
			return false;
		}
		Map<String,Object> attributesMap=new HashMap<String,Object>();
		//list转map
		attributesMap=attributesList.stream().collect(Collectors.toMap(String::toString, Function.identity(),(key1,key2)-> key2));
		//如果有一个dto为空则返回false
		if(SystemCommonF.isEmpty(targetDto)||SystemCommonF.isEmpty(sourceDto)){
			return false;
		}
		//如果两个dto不是同一个类则返回false
		if(targetDto.getClass()!=sourceDto.getClass()){
			return false;
		}
		PropertyDescriptor [] propertyDescriptors= Introspector.getBeanInfo(targetDto.getClass(),Object.class).getPropertyDescriptors();
		//对象复用
		attributesList=new ArrayList<String>();
		for (PropertyDescriptor pd:
				propertyDescriptors) {
			String name=pd.getName();
			if(attributesMap.containsKey(name)){
				Method readObject=pd.getReadMethod();
				Object o1=readObject.invoke(targetDto);
				Object o2=readObject.invoke(sourceDto);
				if(SystemCommonF.isEmpty(o1)&&SystemCommonF.isEmpty(o2)){
					continue;
				}
				if(SystemCommonF.isEmpty(o1)&&!SystemCommonF.isEmpty(o2)){
					attributesList.add(name);
				}
				if(!SystemCommonF.isEmpty(o1)&&SystemCommonF.isEmpty(o2)){
					attributesList.add(name);
				}
				if(!o1.equals(o2)){
					attributesList.add(name);
				}
			}
		}
		if(attributesList.size()==0){
			return true;
		}
		return false;
	}
}