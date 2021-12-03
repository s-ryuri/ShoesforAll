package com.cookandroid.shoesforall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cookandroid.shoesforall.databinding.ActivityPayBinding
import com.iamport.sdk.data.sdk.IamPortRequest
import com.iamport.sdk.data.sdk.PG
import com.iamport.sdk.data.sdk.PayMethod
import com.iamport.sdk.domain.core.Iamport
import java.util.*

class PayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Iamport.init(this)
    }

    override fun onStart() {
        super.onStart()
        val price = intent.getStringExtra("PRICE")
        val item = intent.getStringExtra("ITEM")
        val buyer = intent.getStringExtra("BUYER")

        // SDK 에 결제 요청할 데이터
        val request = price?.let {
            IamPortRequest(
                pg = PG.html5_inicis.makePgRawName(""),         // PG사
                pay_method = PayMethod.card.name,                    // 결제수단
                name = "${item}",                      // 주문명
                merchant_uid = "shoesforall-${Date().time}",     // 주문번호
                amount = "${Integer.parseInt(price)}",                                // 결제금액
                buyer_name ="${buyer}"
            )
        }

        val userCode = "imp67297604"
        Iamport.close()
        // 아임포트 SDK 에 결제 요청하기
        if (request != null) {
            Iamport.payment(userCode, iamPortRequest = request, paymentResultCallback = {
                // 결제 완료 후 결과 콜백
            })
        }

    }
}