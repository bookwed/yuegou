package com.wei.util;

;import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * Author ed
 * Created 2017-08-01 14:10
 */
public class QrCodeUtils {
    public static String transferQrCodeToUri(String content, int width, int height, String fileName, String uploadUrl) throws WriterException, IOException {
        BitMatrix bitMatrix = createBitMatrix(content, width, height);
        bitMatrix = deleteWhite(bitMatrix);
        BufferedImage bufferedImage = toBufferedImage(bitMatrix);
        return writeToFile(bufferedImage, fileName, uploadUrl);
    }

    private static BitMatrix createBitMatrix(String content, int width, int height)throws WriterException {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        return multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
    }

    public static void decodeQrcode(String filePath){
        try {
            URL url = new URL(filePath);
            BufferedImage image = ImageIO.read(url.openConnection().getInputStream());
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
            System.out.println("----tickno:"+result+"====filePath:"+filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static BitMatrix deleteWhite(BitMatrix matrix){
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;
        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }
    private static BufferedImage toBufferedImage(BitMatrix bitMatrix){
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        return image;
    }
    private static String writeToFile(BufferedImage bufferedImage, String fileName, String uploadUrl) throws IOException {
        File file = new File(fileName);
        String uri = "";
        //二维码生成完成之后再上传，避免出现半个二维码的场景
        if(ImageIO.write(bufferedImage, "jpg", file)){
            //TODO 上传图片
            /*Map<String, Object> urlMap = UploadToCdn.postFile(uploadUrl, file);
            if(ObjUtils.isNotEmpty(urlMap)){
                uri = String.format("%s/%s", urlMap.get("savepath").toString(), urlMap.get("savename").toString());
            }*/
            file.delete();
        }
        return uri;
    }

    public static boolean existFile(String fileName){
        File file = new File(fileName);
        if(file.exists()){
            return true;
        }
        return false;
    }
}
