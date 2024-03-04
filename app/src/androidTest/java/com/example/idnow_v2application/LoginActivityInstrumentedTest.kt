
package com.example.idnow_v2application

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityInstrumentedTest {

    @Before
    fun setUp() {
        // Lancement de l'activité avant chaque test
        val intent =
            Intent(ApplicationProvider.getApplicationContext<Context>(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        ApplicationProvider.getApplicationContext<Context>().startActivity(intent)
    }

    @Test
    fun testLoginSuccess() {
        // Saisie du nom d'utilisateur et du mot de passe
        onView(withId(R.id.editTxtUsername)).perform(typeText("atuny0"))
        onView(withId(R.id.editTxtPassword)).perform(typeText("9uQFF1Lh"))

        // Clique sur le bouton de connexion
        onView(withId(R.id.btnLogin)).perform(click())

    }
    @Test
    fun testLoginFailure() {
            // Saisie d'un nom d'utilisateur et d'un mot de passe incorrects
            onView(withId(R.id.editTxtUsername)).perform(typeText("wrong_user"))
            onView(withId(R.id.editTxtPassword)).perform(typeText("wrong_password"))

            // Clique sur le bouton de connexion
            onView(withId(R.id.btnLogin)).perform(click())

    }
}