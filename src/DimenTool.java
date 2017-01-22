/**
 * Created by lapsen_wang on 2017/1/20/0020.
 */


import java.io.*;

/**
 * 本实例以160dpi为基准，
 * 建立了mdpi,hdpi,xhdpi,xxhdpi,xxxhdpi的dp适配文件
 *
 *
 * 使用dpi方法不能保证完全适配
 * 所以现在改为使用px的适配方法
 *
 * 只适配width的情况
 * */

public class DimenTool {

    private final static String rootPath = "E:\\ScreenAdapter\\values-{0}x{1}\\";

    private final static float dw = 320f;
    private final static float dh = 480f;

    private final static String WTemplate = "<dimen name=\"size{0}\">{1}px</dimen>\n";
//    private final static String TTemplate = "<dimen name=\"t{0}\">{1}sp</dimen>\n";

    public static void main(String[] args) {

        makeString(320, 480);
        makeString(480, 800);
        makeString(480, 854);
        makeString(540, 960);
        makeString(600, 1024);
        makeString(720, 1184);
        makeString(720, 1196);
        makeString(720, 1280);
        makeString(768, 1024);
        makeString(800, 1280);
        makeString(1080, 1812);
        makeString(1080, 1920);
        makeString(1440, 2560);
    }

    public static void makeString(int w, int h) {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<resources>\n");

        float cellw = w / dw;

        for (int i = 1; i <= 320; i++) {

            /**屏幕适配*/
            sb.append(WTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellw * i) + ""));

            /**为字体大小设置的适配*/
//            sb.append(TTemplate.replace("{0}", i + "").replace("{1}",
//                    change(cellw * i * 8) + ""));
        }
        sb.append("</resources>");

        String path = rootPath.replace("{0}", h + "").replace("{1}", w + "");
        File rootFile = new File(path);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }

        File layxFile = new File(path + "dimens.xml");

        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sb.toString());
            pw.close();
        } catch (FileNotFoundException e)

        {
            e.printStackTrace();
        }
    }

    public static float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100f;
    }
}

