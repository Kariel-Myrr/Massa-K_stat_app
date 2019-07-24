package com.example.lesson_1.massa_k_app;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

/**
 * Данный класс отвечает за все операции, связанные с bluetooth соединением
 * На данный момент присутствуют метода для включения bluetooth (enableBT()) и отключения bluetooth (disableBT())
 * После окончания работы с объектом данного класса необходимо
 * вызвать метод finish() для закрытия широковещательных каналов
 * <p>
 * В дальнейшем именно сюда будут добавлены методы для передачи и получения данных
 */


public class BluetoothInstruments {

    private BluetoothAdapter mBluetoothAdapter;
    private Activity mainActivity;              // Ссылка на main Activity

    // Конструктор
    // Инициализируем ссылку на main activity и открываем соединение
    public BluetoothInstruments(Activity mainActivity) {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();   // Открываем соединение
        this.mainActivity = mainActivity;                           // Инициализируем ссылку на main activity
    }

    // Деструктор
    // Закрывает широковещательные каналы
    public void finish() {
        mainActivity.unregisterReceiver(mBroadcastReciever1);
    }


    public void enableBT() {
        // Если связь не была установлена, выводим сообщени об ошибке
        if (mBluetoothAdapter == null) {
            Toast.makeText(mainActivity, "Возможно, данное устройство не поддерживает Bluetooth соединение",
                    Toast.LENGTH_SHORT).show();
        }

        // Если bluetooth выключен, включаем
        if (!mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
        }

        // Если bluetooth включён, выводим сообщение об этом
        if (mBluetoothAdapter.isEnabled()) {
            Toast.makeText(mainActivity, "Bluetooth уже включён", Toast.LENGTH_SHORT).show();
        }
    }

    // Отключение bluetooth
    public void disableBT() {
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();

            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            mainActivity.registerReceiver(mBroadcastReciever1, BTIntent);
        }
    }

    // Приёмник шириковещательных сообщений, может пригодиться в дальнейшем
    // На данный момент эта часть кода никак не используется
    private final BroadcastReceiver mBroadcastReciever1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(mBluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, mBluetoothAdapter.ERROR);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Toast.makeText(mainActivity, "State off", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Toast.makeText(mainActivity, "State turning off", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Toast.makeText(mainActivity, "State on", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Toast.makeText(mainActivity, "State turning on", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }
    };
}
