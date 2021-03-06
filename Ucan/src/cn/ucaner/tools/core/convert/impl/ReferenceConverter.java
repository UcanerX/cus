package cn.ucaner.tools.core.convert.impl;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import cn.ucaner.tools.core.convert.AbstractConverter;
import cn.ucaner.tools.core.convert.ConverterRegistry;
import cn.ucaner.tools.core.util.ClassUtil;
import cn.ucaner.tools.core.util.StrUtil;

/**
 * {@link Reference}转换器
 * 
 * @author Looly
 * @since 3.0.8
 */
@SuppressWarnings("rawtypes")
public class ReferenceConverter extends AbstractConverter<Reference> {
	
	private Class<? extends Reference> targetType;
	
	/**
	 * 构造
	 * @param targetType {@link Reference}实现类型
	 */
	public ReferenceConverter(Class<? extends Reference> targetType) {
		this.targetType = targetType;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Reference<?> convertInternal(Object value) {
		
		//尝试将值转换为Reference泛型的类型
		Object targetValue = null;
		final Class<?> paramType = ClassUtil.getTypeArgument(targetType);
		if(null != paramType){
			targetValue = ConverterRegistry.getInstance().convert(paramType, value);
		}
		if(null == targetValue){
			targetValue = value;
		}
		
		if(this.targetType == WeakReference.class){
			return new WeakReference(targetValue);
		}else if(this.targetType == SoftReference.class){
			return new SoftReference(targetValue);
		}
		
		throw new UnsupportedOperationException(StrUtil.format("Unsupport Reference type: {}", this.targetType.getName()));
	}

}
