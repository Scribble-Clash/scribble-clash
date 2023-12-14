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

        public BufferedImage[] handfightanimation() {
                animation = new BufferedImage[7];
                animation[0] = image.getSubimage(640, 0, 64, 64);
                animation[1] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/punchanimation/File1.png");
                animation[2] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/punchanimation/File2.png");
                animation[3] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/punchanimation/File3.png");
                animation[4] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/punchanimation/File4.png");
                animation[5] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/punchanimation/File5.png");
                animation[6] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/punchanimation/File6.png");
                return animation;
        }

        public BufferedImage[] specialhandfightanimation() {
                animation = new BufferedImage[7];
                animation[0] = image.getSubimage(640, 0, 64, 64);
                animation[1] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/punchanimation/File1.png");
                animation[2] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/punchanimation/File2.png");
                animation[3] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/punchanimation/File3.png");
                animation[4] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/punchanimation/File4.png");
                animation[5] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/punchanimation/File5.png");
                animation[6] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/punchanimation/File6.png");
                return animation;
        }

        public BufferedImage[] swingAnimation() {
                animation = new BufferedImage[7];
                animation[0] = image.getSubimage(448, 0, 64, 64);
                animation[1] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/slahanimation/File1.png");
                animation[2] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/slahanimation/File2.png");
                animation[3] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/slahanimation/File3.png");
                animation[4] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/slahanimation/File4.png");
                animation[5] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/slahanimation/File5.png");
                animation[6] = (BufferedImage) Loader.loadImage(
                                "src/assets/Animation/slahanimation/File6.png");
                return animation;
        }

        public BufferedImage[] specialswordanimation() {
                animation = new BufferedImage[20];
                animation[0] = image.getSubimage(448, 0, 64, 64);
                for (int i = 1; i < 20; i++) {
                        animation[i] = (BufferedImage) Loader
                                        .loadImage("src/assets/Animation/SpecialSword/File" + i
                                                        + ".png");
                }
                return animation;
        }

        public BufferedImage[] chargeanimation1() {
                image = (BufferedImage) load.mainimage("../assets/Animation/HandChargePackAnimation.png");
                animation = new BufferedImage[6];
                animation[0] = image.getSubimage(326, 4, 56, 56);
                animation[1] = image.getSubimage(262, 6, 54, 54);
                animation[2] = image.getSubimage(202, 10, 46, 46);
                animation[3] = image.getSubimage(143, 15, 36, 36);
                animation[4] = image.getSubimage(85, 21, 24, 24);
                animation[5] = image.getSubimage(30, 30, 10, 10);
                return animation;
        }

        public BufferedImage[] chargeanimation2() {
                image = (BufferedImage) load.mainimage("../assets/Animation/ChargeAnimation.png");
                animation = new BufferedImage[3];
                animation[0] = image.getSubimage(10, 10, 42, 42);
                animation[1] = image.getSubimage(75, 11, 42, 42);
                animation[2] = image.getSubimage(133, 5, 54, 54);

                return animation;
        }

        public BufferedImage[] blodanimation() {
                animation = new BufferedImage[60];
                for (int i = 0; i < 60; i++) {
                        animation[i] = (BufferedImage) Loader
                                        .loadImage("src/assets/Animation/BlodAnimation/1_" + String.format("%03d", i)
                                                        + ".png");
                }
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
}
