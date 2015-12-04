package com.aidinhut.simpletextcrypt;

import android.content.Context;
import android.content.SharedPreferences;
import android.test.InstrumentationTestCase;

import com.aidinhut.simpletextcrypt.exceptions.WrongPasscodeException;

import junit.framework.Assert;
import org.junit.Test;

/*
 * Test cases for SettingsManager class.
 */
public class SettingsManagerTest extends InstrumentationTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        clearPreferences();
    }

    @Test
    public void testTryGetDefaultPasscode() throws Exception {
        String passcode = SettingsManager.getInstance().tryGetPasscode(
                "1111", getInstrumentation().getTargetContext());

        Assert.assertEquals(passcode.compareTo("1111"), 0);
    }

    @Test
    public void testSettingPasscode() throws Exception {
        String newPass = "184363";
        String defaultPass = "1111";

        SettingsManager.getInstance().setPasscode(newPass, getInstrumentation().getTargetContext());

        // Getting the passcode again.
        String passcode = SettingsManager.getInstance().tryGetPasscode(
                newPass, getInstrumentation().getTargetContext());

        Assert.assertEquals(passcode.compareTo(newPass), 0);

        // Setting the default passcode again.
        SettingsManager.getInstance().setPasscode(
                defaultPass,
                getInstrumentation().getTargetContext());

        // Getting the passcode again.
        passcode = SettingsManager.getInstance().tryGetPasscode(
                defaultPass, getInstrumentation().getTargetContext());

        Assert.assertEquals(passcode.compareTo(defaultPass), 0);
    }

    @Test
    public void testLongPasscode() throws Exception {
        String newPass = "18436309872651726344567609";
        String defaultPass = "1111";

        SettingsManager.getInstance().setPasscode(newPass, getInstrumentation().getTargetContext());

        // Getting the passcode again.
        String passcode = SettingsManager.getInstance().tryGetPasscode(
                newPass, getInstrumentation().getTargetContext());

        Assert.assertEquals(passcode.compareTo(newPass), 0);

        // Setting the default passcode again.
        SettingsManager.getInstance().setPasscode(
                defaultPass,
                getInstrumentation().getTargetContext());

        // Getting the passcode again.
        passcode = SettingsManager.getInstance().tryGetPasscode(
                defaultPass, getInstrumentation().getTargetContext());

        Assert.assertEquals(passcode.compareTo(defaultPass), 0);
    }

    @Test(expected = WrongPasscodeException.class)
    public void testWrongPasscode() throws Exception {
        SettingsManager.getInstance().tryGetPasscode(
                "8888",
                getInstrumentation().getTargetContext());
    }

    @Test
    public void testSettingEncryptionKey() throws Exception {
        String encryptionKey = "ancbdhey7834@#*().,{}}[f1~`f93-+";

        SettingsManager.getInstance().setEncryptionKey(
                encryptionKey,
                getInstrumentation().getTargetContext());

        // Getting the key again and check it.
        String gotEncKey = SettingsManager.getInstance().getEncryptionKey(
                getInstrumentation().getTargetContext());

        Assert.assertEquals(gotEncKey.compareTo(encryptionKey), 0);
    }

    /*
     * Clears all the shared preferences of the current app.
     */
    private void clearPreferences() {
        Context context = getInstrumentation().getTargetContext();
        SharedPreferences sharedPref = context.getSharedPreferences(Constants.PREFERENCES_KEY,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.clear();
        prefEditor.commit();
    }

}