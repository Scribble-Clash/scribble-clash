package animation;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import controller.Loader;

public class Animated {
        BufferedImage image;
        private BufferedImage[] animation;

        Loader load = new Loader();

        public Animated() {
                image = (BufferedImage) load.mainimage();
        }

        public BufferedImage[] swingAnimation() {
                animation = new BufferedImage[7];
                animation[0] = image.getSubimage(448, 0, 64, 64);
                animation[1] = (BufferedImage) Loader.loadImage(
                                "src/assets/slahanimation/File1.png");
                animation[2] = (BufferedImage) Loader.loadImage(
                                "src/assets/slahanimation/File2.png");
                animation[3] = (BufferedImage) Loader.loadImage(
                                "src/assets/slahanimation/File3.png");
                animation[4] = (BufferedImage) Loader.loadImage(
                                "src/assets/slahanimation/File4.png");
                animation[5] = (BufferedImage) Loader.loadImage(
                                "src/assets/slahanimation/File5.png");
                animation[6] = (BufferedImage) Loader.loadImage(
                                "src/assets/slahanimation/File6.png");
                return animation;
        }

        public BufferedImage[] handfightanimation() {
                animation = new BufferedImage[7];
                animation[0] = image.getSubimage(640, 0, 64, 64);
                animation[1] = (BufferedImage) Loader.loadImage(
                                "src/assets/punchanimation/File1.png");
                animation[2] = (BufferedImage) Loader.loadImage(
                                "src/assets/punchanimation/File2.png");
                animation[3] = (BufferedImage) Loader.loadImage(
                                "src/assets/punchanimation/File3.png");
                animation[4] = (BufferedImage) Loader.loadImage(
                                "src/assets/punchanimation/File4.png");
                animation[5] = (BufferedImage) Loader.loadImage(
                                "src/assets/punchanimation/File5.png");
                animation[6] = (BufferedImage) Loader.loadImage(
                                "src/assets/punchanimation/File6.png");
                return animation;
        }

        public BufferedImage[] specialhandfightanimation() {
                animation = new BufferedImage[7];
                animation[0] = image.getSubimage(640, 0, 64, 64);
                animation[1] = (BufferedImage) Loader.loadImage(
                                "src/assets/punchanimation/File1.png");
                animation[2] = (BufferedImage) Loader.loadImage(
                                "src/assets/punchanimation/File2.png");
                animation[3] = (BufferedImage) Loader.loadImage(
                                "src/assets/punchanimation/File3.png");
                animation[4] = (BufferedImage) Loader.loadImage(
                                "src/assets/punchanimation/File4.png");
                animation[5] = (BufferedImage) Loader.loadImage(
                                "src/assets/punchanimation/File5.png");
                animation[6] = (BufferedImage) Loader.loadImage(
                                "src/assets/punchanimation/File6.png");
                return animation;
        }

        public BufferedImage flipImageHorizontally(BufferedImage img) {
                // Pastikan gambar yang dimuat adalah BufferedImage
                if (img instanceof BufferedImage) {
                        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                        tx.translate(-img.getWidth(null), 0);
                        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                        return op.filter(img, null);
                } else {
                        // Jika bukan BufferedImage, konversi terlebih dahulu
                        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null),
                                        BufferedImage.TYPE_INT_ARGB);
                        Graphics2D bGr = bufferedImage.createGraphics();
                        bGr.drawImage(img, 0, 0, null);
                        bGr.dispose();

                        // Kemudian lakukan flipping
                        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                        tx.translate(-bufferedImage.getWidth(null), 0);
                        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                        return op.filter(bufferedImage, null);
                }
        }

        public BufferedImage flipImageVertically(BufferedImage img) {
                if (img instanceof BufferedImage) {
                        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
                        tx.translate(0, -img.getHeight(null));
                        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                        return op.filter(img, null);
                } else {
                        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null),
                                        BufferedImage.TYPE_INT_ARGB);
                        Graphics2D bGr = bufferedImage.createGraphics();
                        bGr.drawImage(img, 0, 0, null);
                        bGr.dispose();

                        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
                        tx.translate(0, -bufferedImage.getHeight(null));
                        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                        return op.filter(bufferedImage, null);
                }
        }

        // Tambah Animasi Di Bawah Ini
}
