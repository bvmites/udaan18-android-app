package com.udaan18.udaan18.android.photo;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.otaliastudios.cameraview.CameraUtils;
import com.udaan18.udaan18.android.R;
import com.udaan18.udaan18.android.util.FileUtil;
import com.xiaopo.flying.sticker.DrawableSticker;
import com.xiaopo.flying.sticker.StickerView;
import com.xiaopo.flying.sticker.TextSticker;

import java.io.File;
import java.lang.ref.WeakReference;


public class PicturePreviewActivity extends AppCompatActivity {

    private static WeakReference<byte[]> image;
    private StickerView stickerView;
    private Toolbar toolbar;
    private Button addText;
    private GridView bottomSheet;
    private Boolean saved;
    private BottomSheetBehavior sheetBehavior;
    private ArrayAdapter<Integer> bottomSheetAdapter;
    private Button showSticker;
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
        showSticker = findViewById(R.id.show_stickers);
        addText = findViewById(R.id.add_stickers);
        bottomSheetAdapter = new BottomSheetAdapter(this, R.layout.item_grid, bottomItems);
        bottomSheet.setAdapter(bottomSheetAdapter);
        saved = false;
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
        addText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testAdd();
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

        showSticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });
        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        setBack();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    public void setBack() {
        toolbar = findViewById(R.id.toolbar_photos);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color_photo)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
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
                if (saved) {

                } else {
                    Toast.makeText(PicturePreviewActivity.this, "Save First",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        } else {
            super.onBackPressed();
        }
    }

    public void testAdd() {
        final TextSticker sticker = new TextSticker(this);
        sticker.setText("Hello, world!");
        sticker.setTextColor(Color.BLUE);
        sticker.setTextAlign(Layout.Alignment.ALIGN_CENTER);
        sticker.resizeText();

        stickerView.addSticker(sticker);
    }


}
