/* Copyright (c) 2000-2003, jMock.org. See LICENSE.txt */
package org.jmock.matcher;

import org.jmock.AbstractTestCase;
import org.jmock.C;
import org.jmock.Constraint;
import org.jmock.dynamic.Invocation;

public class InvocationMatcherTest extends AbstractTestCase {
    private Invocation emptyInvocation =
            new Invocation(Void.class, "example", new Class[0], Void.class, new Object[0]);

    private Invocation exampleInvocation =
            new Invocation(Void.class, "example", new Class[]{String.class}, Void.class,
                    new Object[]{"arg1", "arg2"});

    public void testNameMatchesWhenConstraintIsAnything() {
        MethodNameMatcher matcher = new MethodNameMatcher(C.IS_ANYTHING);
        assertTrue("Should match name", matcher.matches(exampleInvocation));
    }

    public void testNameMatchesWhenConstraintIsNothing() {
        MethodNameMatcher matcher = new MethodNameMatcher(C.not(C.IS_ANYTHING));
        assertFalse("Should not match name", matcher.matches(exampleInvocation));
    }

    public void testNameMatchesGivenString() {
        MethodNameMatcher matcher = new MethodNameMatcher("example");
        assertTrue("Should match name", matcher.matches(exampleInvocation));
    }

    public void testNameDoesNotMatchIncorrectString() {
        MethodNameMatcher matcher = new MethodNameMatcher("not an example");
        assertFalse("Should not match name", matcher.matches(exampleInvocation));
    }

    public void testMatchWhenNoArgumentsOrConstraints() throws Throwable {
        ArgumentsMatcher matcher = new ArgumentsMatcher(new Constraint[0]);

        assertTrue("No arguments", matcher.matches(emptyInvocation));
    }

    public void testNoMatchWhenTooManyArguments() throws Throwable {
        ArgumentsMatcher matcher = new ArgumentsMatcher(new Constraint[0]);

        assertFalse("Too many arguments", matcher.matches(exampleInvocation));
    }

    public void testNoMatchWhenTooFewArguments() throws Throwable {
        ArgumentsMatcher matcher =
                new ArgumentsMatcher(
                        new Constraint[]{C.IS_ANYTHING, C.IS_ANYTHING, C.IS_ANYTHING});

        assertFalse("Too many arguments", matcher.matches(exampleInvocation));
    }

    public void testNoMatchWhenAnyArgumentDoNotConform() throws Throwable {
        ArgumentsMatcher matcher =
                new ArgumentsMatcher(
                        new Constraint[]{C.IS_ANYTHING, C.eq("wrong")});

        assertFalse("Incorrect argument", matcher.matches(exampleInvocation));
    }

    public void testArgumentsMatchWhenAllValuesMatch() throws Throwable {
        ArgumentsMatcher matcher =
                new ArgumentsMatcher(
                        new Constraint[]{C.IS_ANYTHING, C.eq("arg2")});

        assertTrue("Arguments match", matcher.matches(exampleInvocation));
    }


}
