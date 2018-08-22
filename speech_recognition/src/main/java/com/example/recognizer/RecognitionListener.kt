package com.example.recognizer

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.util.Log

class RecognitionListener(speechRecognitionActivity: SpeechRecognitionActivity?) : RecognitionListener {

    val TAG = "RecognitionListener"
    override fun onReadyForSpeech(params: Bundle?) {
        Log.d(TAG, "onReadyForSpeech: $params")
    }

    override fun onRmsChanged(rmsdB: Float) {
//        Log.d(TAG, "onRmsChanged: $rmsdB")
    }

    override fun onBufferReceived(buffer: ByteArray?) {
        Log.d(TAG, "onBufferReceived: $buffer")
    }

    override fun onPartialResults(partialResults: Bundle?) {
        Log.d(TAG, "onPartialResults: $partialResults")
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
        Log.d(TAG, "onEvent: $eventType - $params")
    }

    override fun onBeginningOfSpeech() {
        Log.d(TAG, "onBeginningOfSpeech")
    }

    override fun onEndOfSpeech() {
        Log.d(TAG, "onEndOfSpeech")
    }

    override fun onError(error: Int) {
        Log.d(TAG, "onError: $error")
    }

    override fun onResults(results: Bundle?) {
        Log.d(TAG, "OnResults: ")
        var resultStr = ""
        results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.let {
            for (s: String in it) {
                resultStr += "$s/"
            }
        }
        Log.d(TAG, resultStr)
        var confidentResult = ""
        results?.getFloatArray(SpeechRecognizer.CONFIDENCE_SCORES)?.let {
            for (i: Float in it) {
                confidentResult += "$i/"
            }
        }
        Log.d(TAG, confidentResult)
    }
}