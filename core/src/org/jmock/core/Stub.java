/* Copyright (c) 2000-2003, jMock.org. See LICENSE.txt */
package org.jmock.core;


/**
 * An object that stubs the behaviour of a invokedMethod invocation on behalf of an
 * {@link org.jmock.core.Invokable} object.
 */
public interface Stub
        extends SelfDescribing
{
	/**
	 * Processes the invocation.
	 *
	 * @param invocation The invocation to stub.
	 * @return The result of the invocation, if not throwing an exception.
	 *         Must return <code>null</code> if the invocation is of a invokedMethod with a void return type.
	 * @throws Throwable An exception to be thrown to the caller, if not returning a value.  A checked exception
	 *                   thrown from this invokedMethod must be in the <code>throws</code> list of the invokedObject invokedMethod.
	 */
	Object invoke( Invocation invocation ) throws Throwable;
}
