/**
 * Created by lapsen_wang on 2017/1/20/0020.
 */


import java.io.*;

/**
 * 本实例以160dpi为基准，
 * 建立了mdpi,hdpi,xhdpi,xxhdpi,xxxhdpi的dp适配文件
 * */

public class DimenTool {

    private final static String rootPath = "E:\\ScreenAdapter\\values-{0}\\";

    private final static float dw = 160f;

    private final static String WTemplate = "<dimen name=\"x{0}\">{1}dp</dimen>\n";
    private final static String TTemplate = "<dimen name=\"t{0}\">{1}sp</dimen>\n";

    public static void main(String[] args) {

        makeString(160);
        makeString(240);
        makeString(320);
        makeString(480);
        makeString(640);
    }

    public static void makeString(int w) {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<resources>\n");

        float cellw = w / dw;

        for (int i = 1; i <= 320; i++) {

            /**屏幕适配*/
            sb.append(WTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellw * i) + ""));

            /**为字体大小设置的适配*/
            sb.append(TTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellw * i * 8) + ""));
        }
        sb.append("</resources>");

        String path = "";
        switch (w){
            case 160:
                path = rootPath.replace("{0}", "mdpi");
                break;
            case 240:
                path = rootPath.replace("{0}", "hdpi");
                break;
            case 320:
                path = rootPath.replace("{0}", "xhdpi");
                break;
            case 480:
                path = rootPath.replace("{0}", "xxhdpi");
                break;
            case 640:
                path = rootPath.replace("{0}", "xxxhdpi");
                break;
        }

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

