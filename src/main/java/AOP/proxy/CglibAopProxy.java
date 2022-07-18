package AOP.proxy;

import AOP.AdvisedSupport;
import AOP.ReflectiveMethodInvocation;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

/**
 * 基于Cglib的动态代理的具体实现
 */
public class CglibAopProxy implements AopProxy {
	private final AdvisedSupport advisedSupport;

	public CglibAopProxy(AdvisedSupport advisedSupport) {
		this.advisedSupport = advisedSupport;
	}

	/**
	 * 使用Enhancer类，底层使用ASM字节码增强技术处理类，并生成代理对象
	 * @return
	 */
	@Override
	public Object getProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(advisedSupport.getTargetSource().getTarget().getClass());
		enhancer.setInterfaces(advisedSupport.getTargetSource().getTargetInterfaces());
		enhancer.setCallback(new DynamicAdvisedInterceptor(advisedSupport));
		return enhancer.create();
	}

	private static class DynamicAdvisedInterceptor implements MethodInterceptor {
		private final AdvisedSupport advisedSupport;

		public DynamicAdvisedInterceptor(AdvisedSupport advisedSupport) {
			this.advisedSupport = advisedSupport;
		}

		/**
		 * 重写MethodInterceptor的intercept方法，
		 * @param o 被增强对象
		 * @param method 被增强方法
		 * @param args 参数数组
		 * @param methodProxy MethodProxy对象
		 * @return
		 * @throws Throwable
		 */
		@Override
		public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
			CglibMethodInvocation methodInvocation = new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, args, methodProxy);

			//若原方法匹配，则执行增强
			if(advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getTarget().getClass())) {
				return advisedSupport.getMethodInterceptor().invoke(methodInvocation);
			}

			//否则执行原方法
			return methodInvocation.proceed();
		}
	}

	private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
		private final MethodProxy methodProxy;

		public CglibMethodInvocation(Object target, Method method, Object[] args, MethodProxy methodProxy) {
			super(target, method, args);
			this.methodProxy = methodProxy;
		}

		/**
		 * 重写父类的proceed，通过MethodProxy对象调用原方法
		 * @return
		 * @throws Throwable
		 */
		@Override
		public Object proceed() throws Throwable {
			return this.methodProxy.invoke(this.target, this.args);
		}
	}

}
