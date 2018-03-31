package com.udaan18.udaan18.android.photo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.otaliastudios.cameraview.CameraUtils;
import com.rtugeek.android.colorseekbar.ColorSeekBar;
import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.util.FileUtil;
import com.xiaopo.flying.sticker.DrawableSticker;
import com.xiaopo.flying.sticker.StickerView;
import com.xiaopo.flying.sticker.TextSticker;

import java.io.File;
import java.lang.ref.WeakReference;


public class PicturePreviewActivity extends AppCompatActivity implements View.OnClickListener {

    private static WeakReference<byte[]> image;
    private StickerView stickerView;

    private GridView bottomSheet;
    private Boolean saved;
    private TextSticker stickerText;
    private BottomSheetBehavior sheetBehavior;

    private ImageView close;
    private ImageView sticker;
    private ImageView text;
    private ImageView save;
    private ImageView share;
    private EditText valueText;

    private ArrayAdapter<Integer> bottomSheetAdapter;

    private ColorSeekBar bar;
    private Integer[] bottomItems = {R.drawable.filter_1, R.drawable.filter_2, R.drawable.filter_3, R.drawable.filter_4,
            R.drawable.filter_5, R.drawable.filter_6, R.drawable.filter_7, R.drawable.filter_8,
            R.drawable.filter_9, R.drawable.filter_10, R.drawable.filter_11};

    public static void setImage(@Nullable byte[] im) {
        image = im != null ? new WeakReference<>(im) : null;
    }

    private static float getApproximateFileMegabytes(Bitmap bitmap) {
        return (bitmap.getRowBytes() * bitmap.getHeight()) / 1024 / 1024;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_preview);
        final ImageView imageView = findViewById(R.id.image);
        stickerView = findViewById(R.id.stkr);
        bottomSheet = (GridView) findViewById(R.id.bottom_sheet);
        bar = findViewById(R.id.colorSlider);
        bottomSheetAdapter = new BottomSheetAdapter(this, R.layout.item_grid, bottomItems);
        bottomSheet.setAdapter(bottomSheetAdapter);
        saved = false;

        close = findViewById(R.id.close_preview);
        sticker = findViewById(R.id.sticker);
        text = findViewById(R.id.text_on_img);
        save = findViewById(R.id.item_save);
        share = findViewById(R.id.item_share);

        close.setOnClickListener(this);
        sticker.setOnClickListener(this);
        text.setOnClickListener(this);
        save.setOnClickListener(this);
        share.setOnClickListener(this);

        bottomSheet.setTranslationY(getStatusBarHeight());
        sheetBehavior = BottomSheetBehavior.from(bottomSheet);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            boolean first = true;

            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                Log.d("MainActivity", "onStateChanged: " + newState);
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                Log.d("MainActivity", "onSlide: ");
                if (first) {
                    first = false;
                    bottomSheet.setTranslationY(0);
                }
            }
        });
        stickerText = new TextSticker(this);
        bar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            @Override
            public void onColorChangeListener(int colorBarPosition, int alphaBarPosition, int color) {
                stickerText.setTextColor(color);
                //colorSeekBar.getAlphaValue();
            }
        });

        bottomSheet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Drawable drawable =
                        ContextCompat.getDrawable(getApplicationContext(), bottomItems[position]);
                stickerView.addSticker(new DrawableSticker(drawable));
                sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });

        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        //saveFile();
        final long delay = getIntent().getLongExtra("delay", 0);
        final int nativeWidth = getIntent().getIntExtra("nativeWidth", 0);
        final int nativeHeight = getIntent().getIntExtra("nativeHeight", 0);
        byte[] b = image == null ? null : image.get();
        if (b == null) {
            finish();
            return;
        }


        CameraUtils.decodeBitmap(b, 1000, 1000, new CameraUtils.BitmapCallback() {
            @Override
            public void onBitmapReady(Bitmap bitmap) {

                imageView.setImageBitmap(bitmap);
            }
        });

    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        } else {
            super.onBackPressed();
        }
    }

    void alertForText() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertTheme);

        builder
                .setView(R.layout.edit_text)
                .setCancelable(true)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Toast.makeText(PicturePreviewActivity.this, "here", Toast.LENGTH_SHORT).show();

                                stickerText = new TextSticker(getApplicationContext());
                                stickerText.setText(valueText.getText().toString());
                                stickerText.setTextAlign(Layout.Alignment.ALIGN_CENTER);
                                stickerText.resizeText();
                                stickerView.addSticker(stickerText);
                            }
                        }
                )
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }
                );
        AlertDialog alert = builder.create();
        alert.show();
    }

    void getText() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_text, null);
        builder.setView(view);

        valueText = view.findViewById(R.id.text_for_sticker);
        final AlertDialog dialog = builder.create();
        dialog.show();
        Button bn = view.findViewById(R.id.submit_alert);
        Button close = view.findViewById(R.id.cancle_alert);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stickerText = new TextSticker(getApplicationContext());
                stickerText.setText(valueText.getText().toString());
                stickerText.setTextAlign(Layout.Alignment.ALIGN_CENTER);
                stickerText.resizeText();
                stickerView.addSticker(stickerText);
                dialog.dismiss();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_save:
                if (!saved) {
                    saved = true;
                    File file = FileUtil.getNewFile(PicturePreviewActivity.this, "Sticker");
                    if (file != null) {
                        stickerView.save(file);
                        Toast.makeText(PicturePreviewActivity.this, "Saved",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PicturePreviewActivity.this, "the file is null", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PicturePreviewActivity.this, "Already Saved",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.item_share:
                String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(), stickerView.createBitmap(), "title", null);
                Uri bitmapUri = Uri.parse(bitmapPath);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
                startActivity(Intent.createChooser(intent, "Share"));
                Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sticker:
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.text_on_img:
                getText();
                break;
            case R.id.close_preview:
                onBackPressed();
                break;
        }
    }
}
