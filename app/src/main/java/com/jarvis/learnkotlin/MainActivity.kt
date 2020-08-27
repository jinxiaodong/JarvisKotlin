package com.jarvis.learnkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.jarvis.baselibrary.utils.SpUtil
import com.jarvis.baselibrary.utils.Utils
import com.jarvis.learnkotlin.entity.User
import com.jarvis.learnkotlin.widget.CodeView

/**
 * @author jinxiaodong
 * @description：TODO
 * @date 2020/8/20
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {


    private val usernameKey = "username"
    private val passwordKey = "password"

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etCode: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        etCode = findViewById(R.id.et_code)

        etUsername.setText(SpUtil.get(usernameKey, ""))
//        etUsername.text = Editable.Factory.getInstance().newEditable("qa")
        etPassword.setText(SpUtil.get(passwordKey, ""))

        val btnLogin = findViewById<Button>(R.id.btn_login)
        val codeView = findViewById<CodeView>(R.id.code_view)
        btnLogin.setOnClickListener(this)
        codeView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v is CodeView) {
            v.updateCode()
        } else if (v is Button) {
            login()
        }


    }


    private fun login() {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val code = etCode.text.toString().trim()
        val user = User(username, password, code)
        if (verify(user)) {
            SpUtil.save(usernameKey, username)
            SpUtil.save(passwordKey, password)
        }

        startActivity(Intent(this, LessonActivity::class.java))
    }

    fun verify(user: User): Boolean {
        if (user.username?.length ?: 0 < 4) {
            Utils.toast("用户名不合法")
            return false
        }
        if (user.password?.length ?: 0 < 4) {
            Utils.toast("密码不合法")

            return false
        }

        return true
    }
}