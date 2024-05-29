package executerService;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuterServiceTask {

	public static void test(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 1; i < 1000; i++) {

			executorService.execute(runnableTask("Task " + i));
		}

		executorService.shutdown();

	}

	public static Runnable runnableTask(String msg) {
		return new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " : " + msg);
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
	}

	public static void main(String[] args) {
		Map<String, Integer> test = new HashMap<String, Integer>();
		ParameterizedType superclass = (ParameterizedType) test.getClass().getGenericSuperclass();
		System.out.println(superclass);

		Type[] typeArgs = superclass.getActualTypeArguments();
		System.out.println(typeArgs[0]);

	}

}
