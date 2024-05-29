package tests;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheTest<K, V> {
	private Class<K> keyClass;
	private Class<V> valueClass;

	public CacheTest() {
//		Type genericSuperclass = getClass().getGenericSuperclass();
//		if (genericSuperclass instanceof ParameterizedType) {
//			ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
//			Type[] typeArgs = parameterizedType.getActualTypeArguments();
//			this.keyClass = (Class<K>) typeArgs[0];
//			this.valueClass = (Class<V>) typeArgs[1];
//		} else {
//			throw new IllegalArgumentException("Superclass is not parameterized.");
//		}
	}

	// Getter methods for keyClass and valueClass
//	public Class<K> getKeyClass() {
//		return keyClass;
//	}
//
//	public Class<V> getValueClass() {
//		return valueClass;
//	}

	public static void main(String[] args) throws ClassNotFoundException {
		CacheTest<String, Integer> cacheTest = new CacheTest<String, Integer>();

//		List<String> list = new ArrayList<String>(); // creates a generic sub-type
//		final Class type = (Class) ((ParameterizedType) list.getClass().getGenericSuperclass())
//				.getActualTypeArguments()[0];
//		System.out.println(getGenericType(list));
		Map<String, Integer> map = new HashMap<String, Integer>();

		System.out.print(map.getClass().getTypeName());
	}

	public static Class<?> getGenericType(Object action) throws ClassNotFoundException {
		Type type = ((ParameterizedType) action.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		String sType[] = type.toString().split(" ");

		for (String i : sType) {
			System.out.println(i);
		}

		return Class.forName(sType[0]);
	}
}
