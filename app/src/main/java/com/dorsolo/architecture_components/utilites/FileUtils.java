package com.dorsolo.architecture_components.utilites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import com.dorsolo.architecture_components.utilites.Constants.Files;

import java.io.ByteArrayOutputStream;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import androidx.core.app.ActivityCompat;

public final class FileUtils {

    private FileUtils() {
    }

    @Nullable
    public static Bitmap drawableToBitmap(@NonNull Context context, @DrawableRes int drawableRes) {
        Drawable drawable = ActivityCompat.getDrawable(context, drawableRes);
        if (drawable == null)
            return null;
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * Convert base64 String data format to a byte array data format
     *
     * @param base64 the String to be converted to byte array
     * @return the converted base64 string now in byte array format
     */
    private static byte[] base64StringToByteArray(@NonNull String base64) {
        return Base64.decode(base64, Base64.NO_WRAP);
    }

    /**
     * Convert byte array data format to a Bitmap object
     *
     * @param bytes the byte array to be converted to Bitmap object
     * @return the converted byte array now is Bitmap format
     */
    @NonNull
    private static Bitmap byteArrayToBitmap(@NonNull byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * Convert Bitmap to byte array data format
     *
     * @param picture the Bitmap to be converted to byte array
     * @param quality Hint to the compressor, int val 0 - 100. 0 meaning compress for small size, 100 meaning compress for max quality
     * @return byte array data format which is the converted Bitmap
     */
    @NonNull
    private static byte[] bitmapToByteArray(@NonNull Bitmap picture, @Size(min = Files.MIN_QUALITY, max = Files.MAX_QUALITY) int quality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        picture.compress(Bitmap.CompressFormat.JPEG, quality, stream);
        return stream.toByteArray();
    }

    /**
     * Convert byte array to Base64 String
     *
     * @param bytes the byte array data format to be converted to Base64 String
     * @return the Base64 String data format which is the converted byte array
     */
    @NonNull
    private static String byteArrayToBase64String(@NonNull byte[] bytes) {
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    /**
     * Perform the full conversion from basse64 String to Bitmap obj instance
     *
     * @param base64 String value which represent an image in a base64 format
     * @return Bitmap obj instance which is the base64 string converted Bitmap
     */
    @NonNull
    public static Bitmap base64ToBitmap(@NonNull String base64) {
        return byteArrayToBitmap(base64StringToByteArray(base64));
    }

    /**
     * Perform the full conversion from Bitmap to base64 String
     *
     * @param picture Bitmap obj instance to be converted to Base64 String
     * @param quality Integer value between 0 - 100 which represent the compress quality
     * @return String value which is the base64 format picture
     */
    @NonNull
    public static String bitmapToBase64(@NonNull Bitmap picture, @Size(min = Files.MIN_QUALITY, max = Files.MAX_QUALITY) int quality) {
        return byteArrayToBase64String(bitmapToByteArray(picture, quality));
    }
}