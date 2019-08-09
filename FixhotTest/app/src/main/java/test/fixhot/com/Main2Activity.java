package test.fixhot.com;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hotfix.library.utils.FixDexUtil;
import com.hotfix.library.utils.Constants;
import com.hotfix.library.utils.FileUtils;

import java.io.File;
import java.io.IOException;

import test.fixhot.com.err.Utils;

public class Main2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void err(View view) {

        try {
            Utils utils=new Utils();
            utils.show();
            Log.e("Main2Activity","");
            Toast.makeText(this,"无异常",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,"异常了1",Toast.LENGTH_SHORT).show();
        }

    }

    public void fix(View view) {
        fix();
    }


    private void fix() {

        //SDK找修复包
        File sourceFile = new File(Environment.getExternalStorageDirectory(), Constants.DEX_NAME);
        //目标路径,私有目录
        File tarageFile = new File(getDir(Constants.DEX_DIR, Context.MODE_PRIVATE).getAbsolutePath() + File.separator + Constants.DEX_NAME);
        //如果之前修复的hex存在,则删除
        if (tarageFile.exists()) {
            tarageFile.delete();
        }


        try {
            //将下载的修复包,复制到私有目录,在做解压工作
            FileUtils.copyFile(sourceFile, tarageFile);
            //开始修复
            FixDexUtil.loadFixedDex(this);
            //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
