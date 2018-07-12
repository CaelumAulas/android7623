package br.com.caelum.listacontatos.servico;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.caelum.listacontatos.dao.AlunoDao;

public class SMSListener extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {


        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];


        SmsMessage sms = SmsMessage.createFromPdu(pdu);
        String telefoneDoSMS = sms.getDisplayOriginatingAddress();

        AlunoDao alunoDao = new AlunoDao(context);
        boolean smsEhDeUmAluno = alunoDao.consultaTelefone(telefoneDoSMS);

        if (smsEhDeUmAluno) {
            Toast.makeText(context, "Tocando a marcha imperial", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
