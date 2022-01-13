package com.example.helper;
//Ezel Karadirek
import static junit.framework.TestCase.assertNotNull;

import android.app.Activity;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class RegisterTest {

    @Rule
    public ActivityTestRule<Register> testRule= new ActivityTestRule<Register>(Register.class);

    private Register register=null;
    @Before
    public void setUp() throws Exception {
        register= testRule.getActivity();
    }
    @After
    public void tearDown() throws Exception {
        register=null;
    }
    @Test
    public void testOnCreate() {
        View view= register.findViewById(R.id.btnRegister);
        assertNotNull(view);
    }
}