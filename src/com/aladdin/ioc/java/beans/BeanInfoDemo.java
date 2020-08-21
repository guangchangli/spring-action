package com.aladdin.ioc.java.beans;

import java.beans.*;
import java.util.stream.Stream;

/**
 * @author lgc
 */
public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class,Object.class);
        Stream.of(beanInfo.getPropertyDescriptors())
                //properDescriptor 允许添加属性编辑器 - PropertyEditor
                .forEach(properDescriptor->{
                    Class<?> propertyType=properDescriptor.getPropertyType();
                    String propertyName = properDescriptor.getName();
                    if ("age".equalsIgnoreCase(propertyName)){// 为 age 字段/属性 增加 PropertyEditor
                        properDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                        properDescriptor.createPropertyEditor(properDescriptor);
                    }
                });
    }

    static class StringToIntegerPropertyEditor extends PropertyEditorSupport{
        @Override
        public void setAsText(String text) throws java.lang.IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }
}
