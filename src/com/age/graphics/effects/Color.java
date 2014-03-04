package com.age.graphics.effects;

import java.net.CookieHandler;

import static org.lwjgl.opengl.GL11.glColor4f;

public class Color {

    /*
     * A massive thanks to cloford.com for hosting a list of colours and their RGB values
     *	http://www.cloford.com/resources/colours/500col.htm
    */
    public static final Color WHITE = new Color(1F, 1F, 1F), BLACK = new Color(0F, 0F, 0F), RED = new Color(1F, 0F, 0F), GREEN = new Color(0, 128, 0), BLUE = new Color(0F, 0F, 1F);
    public static final Color RED_INDIAN = new Color(176, 23, 31), CRIMSON = new Color(220, 20, 60), PINK_LIGHT = new Color(255, 182, 193), PINK_LIGHT_2 = new Color(255, 174, 185);
    public static final Color PINK_LIGHT_3 = new Color(238, 162, 173), PINK_LIGHT_4 = new Color(205, 140, 149), PINK_LIGHT_5 = new Color(139, 95, 101), PINK = new Color(255, 192, 203);
    /*Pink_2(255, 181, 197), Pink_3(238, 169, 184), Pink_4(205, 145, 158), Pink_5(139, 99, 108), Violet_Red_Pale(219, 112, 147),
    Violet_Red_Pale_2(255, 130, 171), Violet_Red_Pale_3(238, 121, 159), Violet_Red_Pale_4(205, 104, 137), Violet_Red_Pale_5(139, 71, 93),
    Lavender_Blush(255, 240, 245), Lavender_Blush_2(238, 224, 229), Lavender_Blush_3(205, 193, 197), Lavender_Blush_4(139, 131, 134),
    Violet_Red_2(255, 62, 150), Violet_Red_3(238, 58, 140), Violet_Red_4(205, 50, 120), Violet_Red_5(139, 34, 82), Pink_Hot(255, 105, 180),
    Pink_Hot_2(255, 110, 180), Pink_Hot_3(238, 106, 167), Pink_Hot_4(205, 96, 144), Pink_Hot_5(139, 58, 98), Raspberry(135, 38, 87),
    Pink_Deep(255, 20, 147), Pink_Deep_2(238, 18, 137), Pink_Deep_3(205, 16, 118), Pink_Deep_4(139, 10, 80), Maroon(255, 52, 179),
    Maroon_2(238, 48, 167), Maroon_3(205, 41, 144), Maroon_4(139, 28, 98), Violet_Red_Medium(199, 21, 133), Violet_Red(208, 32, 144),
    Orchid(218, 112, 214), Orchid_2(255, 131, 250), Orchid_3(238, 122, 233), Orchid_4(205, 105, 201), Orchid_5(139, 71, 137),
    Thistle(216, 191, 216), Thistle_2(255, 225, 255), Thistle_3(238, 210, 238), Thistle_4(205, 181, 205), Thistle_5(139, 123, 139),
    Plum_2(255, 187, 255), Plum_3(238, 174, 238), Plum_4(205, 150, 205), Plum_5(139, 102, 139), Plum(221, 160, 221), Violet(238, 130, 238),
    Magenta(255, 0, 255), Magenta_2(238, 0, 238), Magenta_3(205, 0, 205), Magenta_Dark(139, 0, 139), Purple(128, 0, 128), Orchid_Medium(186, 85, 211),
    Orchid_Medium_2(224, 102, 255), Orchid_Medium_3(209, 95, 238), Orchid_Medium_4(180, 82, 205), Orchid_Medium_5(122, 55, 139), Violet_Dark(148, 0, 211),
    Orchid_Dark(153, 50, 204), Orchid_Dark_2(191, 62, 255), Orchid_Dark_3(178, 58, 238), Orchid_Dark_4(154, 50, 205), Orchid_Dark_5(104, 34, 139),
    Indigo(75, 0, 130), BlueViolet(138, 43, 226), Purple_2(155, 48, 255), Purple_3(145, 44, 238), Purple_4(125, 38, 205), Purple_5(85, 26, 139),
    Purple_Medium(147, 112, 219), Purple_Medium_2(171, 130, 255), Purple_Medium_3(159, 121, 238), Purple_Medium_4(137, 104, 205),
    Purple_Medium_5(93, 71, 139), Blue_Slate_Dark(72, 61, 139), Blue_Slate_Light(132, 112, 255), Blue_Slate_Medium(123, 104, 238),
    Blue_Slate(106, 90, 205), Blue_Slate_2(131, 111, 255), Blue_Slate_3(122, 103, 238), Blue_Slate_4(105, 89, 205), Blue_Slate_5(71, 60, 139),
    White_Ghost(248, 248, 255), Lavender(230, 230, 250), Blue_2(0, 0, 238), Blue_Medium(0, 0, 205), Blue_Dark(0, 0, 139), Blue_Navy(0, 0, 128),
    Blue_Midnight(25, 25, 112), Cobalt(61, 89, 171), Blue_Royal(65, 105, 225), Blue_Royal_2(72, 118, 255), Blue_Royal_3(67, 110, 238),
    Blue_Royal_4(58, 95, 205), Blue_Royal_5(39, 64, 139), Blue_Cornflower(100, 149, 237), Blue_Steel_Light(176, 196, 222),
    Blue_Steel_Light_2(202, 225, 255), Blue_Steel_Light_3(188, 210, 238), Blue_Steel_Light_4(162, 181, 205), Blue_Steel_Light_5(110, 123, 139),
    Gray_Slate_Light(119, 136, 153), Gray_Slate(112, 128, 144), Gray_Slate_2(198, 226, 255), Gray_Slate_3(185, 211, 238),
    Gray_Slate_4(159, 182, 205), Gray_Slate_5(108, 123, 139), Blue_Dodger_2(30, 144, 255), Blue_Dodger_3(28, 134, 238),
    Blue_Dodger_4(24, 116, 205), Blue_Dodger_5(16, 78, 139), Blue_Alice(240, 248, 255), Blue_Steel(70, 130, 180),
    Blue_Steel_2(99, 184, 255), Blue_Steel_3(92, 172, 238), Blue_Steel_4(79, 148, 205), Blue_Steel_5(54, 100, 139),
    Blue_Sky_Light(135, 206, 250), Blue_Sky_Light_2(176, 226, 255), Blue_Sky_Light_3(164, 211, 238), Blue_Sky_Light_4(141, 182, 205),
    Blue_Sky_Light_5(96, 123, 139), Blue_Sky_2(135, 206, 255), Blue_Sky_3(126, 192, 238), Blue_Sky_4(108, 166, 205),
    Blue_Sky_5(74, 112, 139), Blue_Sky(135, 206, 235), Blue_Sky_Deep_2(0, 191, 255), Blue_Sky_Deep_3(0, 178, 238),
    Blue_Sky_Deep_4(0, 156, 205), Blue_Sky_Deep_5(0, 104, 139), Peacock(51, 161, 201), Blue_Light(173, 216, 230),
    Blue_Light_2(191, 239, 255), Blue_Light_3(178, 239, 255), Blue_Light_4(154, 192, 205), Blue_Light_5(104, 131, 139),
    Blue_Powder(176, 224, 230), Blue_Cadet_2(152, 245, 255), Blue_Cadet_3(178, 223, 238), Blue_Cadet_4(122, 197, 205),
    Blue_Cadet_5(83, 134, 139), Turquoise_2(0, 245, 255), Turquoise_3(0, 229, 238), Turquoise_4(0, 197, 205),
    Turquoise_5(0, 134, 139), Blue_Cadet(95, 158, 160), Turquoise_Dark(0, 206, 209), Azure_2(240, 255, 255), Azure_3(224, 238, 238),
    Azure_4(193, 205, 205), Azure_5(131, 139, 139), Cyan_Light_2(224, 255, 255), Cyan_Light_3(209, 238, 238),
    Cyan_Light_4(180, 205, 205), Cyan_Light_5(122, 139, 139), Turquoise_Pale_2(187, 255, 255), Turquoise_Pale_3(174, 238, 238),
    Turquoise_Pale_4(150, 205, 205), Turquoise_Pale_5(102, 139, 139), Gray_Slate_Dark(47, 79, 79),
    Gray_Slate_Dark_2(151, 255, 255), Gray_Slate_Dark_3(141, 238, 238), Gray_Slate_Dark_4(121, 205, 205),
    Gray_Slate_Dark_5(82, 139, 139), Cyan(0, 255, 255), Aqua(0, 255, 255), Cyan_2(0, 238, 238), Cyan_3(0, 205, 205),
    Cyan_4(0, 139, 139), Teal(0, 128, 128), Turquoise_Medium(72, 209, 204), Green_Sea_Light(32, 178, 170),
    Blue_Manganese(3, 168, 158), Turquoise(64, 224, 208), Grey_Cold(128, 138, 135), Blue_Turquoise(0, 199, 140),
    Aqua_Marine_2(127, 255, 212), Aqua_Marine_3(118, 238, 198), Aqua_Marine_4(102, 205, 170), Aqua_Marine_5(69, 139, 116),
    Green_Spring_Medium(0, 250, 154), Mint_Cream(245, 255, 250), Green_Spring(0, 255, 127), Green_Spring_2(0, 238, 118),
    Green_Spring_3(0, 205, 102), Green_Spring_4(0, 139, 69), Green_Sea_Medium(60, 179, 113), Green_Sea_2(84, 255, 159),
    Green_Sea_3(78, 238, 148), Green_Sea_4(67, 205, 128), Green_Sea_5(46, 139, 87), Green_Emerald(0, 201, 87),
    Mint(189, 252, 201), Green_Cobalt(61, 145, 64), Honeydew_2(240, 255, 240), Honeydew_3(224, 238, 224), Honeydew_4(193, 205, 193),
    Honeydew_5(131, 139, 131), Green_Sea_Dark(143, 188, 143), Green_Sea_Dark_2(193, 255, 193),
    Green_Sea_Dark_3(180, 238, 180), Green_Sea_Dark_4(155, 205, 155), Green_Sea_Dark_5(105, 139, 105),
    Green_Pale(152, 251, 152), Green_Pale_2(154, 255, 154), Green_Pale_3(144, 238, 144), Green_Pale_4(124, 205, 124),
    Green_Pale_5(84, 139, 84), Green_Lime(50, 205, 50), Green_Forest(34, 139, 34), Green_2(0, 238, 0),
    Green_3(0, 205, 0), Green_4(0, 139, 0), Green_Dark(0, 100, 0), Green_Sap(48, 128, 20), Green_Lawn(124, 252, 0),
    Chartreuse_2(127, 252, 0), Chartreuse_3(118, 238, 0), Chartreuse_4(102, 205, 0), Chartreuse_5(69, 139, 0),
    Green_Yellow(173, 255, 47), Green_Olive_Dark_2(202, 255, 112), Green_Olive_Dark_3(188, 238, 104),
    Green_Olive_Dark_4(162, 205, 90), Green_Olive_Dark_5(110, 139, 61), Green_Olive_Dark(85, 107, 47),
    Olive_Drab(107, 142, 35), Olive_Drab_2(192, 255, 62), Olive_Drab_3(179, 238, 58), Olive_Drab_4(154, 205, 50),
    Olive_Drab_5(105, 139, 34), Ivory_2(255, 255, 240), Ivory_3(238, 238, 240), Ivory_4(205, 205, 193), Ivory_5(139, 139, 131),
    Beige(245, 245, 220), Yellow_Light_2(255, 255, 224), Yellow_Light_3(238, 238, 209), Yellow_Light_4(205, 205, 180),
    Yellow_Light_5(139, 139, 122), Yellow_Golden_Light(250, 250, 210), Yellow(255, 255, 0), Yellow_2(238, 238, 0),
    Yellow_3(205, 205, 0), Yellow_4(139, 139, 0), Grey_Warm(128, 128, 105), Olive(128, 128, 0), Khaki_Dark(189, 189, 107),
    Khaki_2(255, 246, 143), Khaki_3(238, 230, 133), Khaki_4(205, 198, 115), Khaki_5(139, 134, 78),
    Khaki(240, 230, 140), Rod_Golden_Pale(238, 232, 170), Lemon_Chiffon_2(255, 255, 205), Lemon_Chiffon_3(238, 233, 191),
    Lemon_Chiffon_4(205, 201, 165), Lemon_Chiffon_5(139, 137, 112), Rod_Golden_Pale_2(255, 236, 139),
    Rod_Golden_Pale_3(238, 220, 130), Rod_Golden_Pale_4(205, 190, 112), Rod_Golden_Pale_5(139, 129, 87),
    Banana(227, 207, 87), Gold(255, 215, 0), Gold_2(238, 201, 0), Gold_3(139, 117, 0), Cornsilk(255, 248, 220),
    Cornsilk_2(238, 232, 205), Cornsilk_3(205, 200, 177), Cornsilk_4(139, 136, 120), Rod_Golden(218, 165, 32),
    Rod_Golden_2(255, 193, 37), Rod_Golden_3(238, 180, 34), Rod_Golden_4(205, 155, 29), Rod_Golden_5(139, 105, 20),
    Rod_Golden_Dark(184, 134, 11), Rod_Golden_Dark_2(255, 185, 11), Rod_Golden_Dark_3(238, 174, 14), Rod_Golden_Dark_4(205, 149, 12),
    Rod_Golden_Dark_5(139, 101, 8), Orange(255, 165, 0), Orange_2(238, 154, 0), Orange_3(205, 133, 0), Orange_4(139, 90, 0),
    White_Floral(255, 250, 240), Lace_Old(253, 245, 230), Wheat(245, 222, 179), Wheat_2(255, 231, 186),
    Wheat_3(238, 216, 174), Wheat_4(205, 186, 150), Wheat_5(139, 126, 102), Moccasin(255, 227, 191),
    Papaya_Whip(255, 239, 213), Almond_Bleached(255, 235, 205), White_Navajo(255, 222, 173),
    White_Navajo_2(238, 207, 161), White_Navajo_3(205, 179, 139), White_Navajo_4(139, 121, 94),
    Egg_Shell(252, 230, 201), Tan(210, 180, 140), Brick(156, 102, 31), Yellow_Candium(255, 153, 18),
    White_Antique(250, 235, 215), White_Antique_2(255, 239, 219), White_Antique_3(238, 223, 204),
    White_Antique_4(205, 192, 176), White_Antique_5(139, 131, 120), Wood_Burly(222, 184, 135),
    Wood_Burly_2(255, 211, 155), Wood_Burly_3(238, 197, 145), Wood_Burly_4(205, 170, 125),
    Wood_Burly_5(139, 115, 85), Bisque(255, 228, 196), Bisque_2(238, 213, 183), Bisque_3(205, 183, 158), Bisque_4(139, 125, 107),
    Melon(227, 168, 105), Carrot(237, 145, 33), Orange_Dark(255, 140, 0), Orange_Dark_2(255, 127, 0), Orange_Dark_3(238, 118, 0),
    Orange_Dark_4(139, 69, 0), Tan_2(255, 165, 79), Tan_3(238, 154, 73), Tan_4(205, 133, 63), Tan_5(139, 90, 43),
    Linen(250, 240, 230), Peach_Puff(255, 218, 185), Peach_Puff_2(238, 203, 173), Peach_Puff_3(205, 175, 149),
    Peach_Puff_4(139, 119, 101), Sea_Shell(255, 245, 238), Sea_Shell_2(238, 229, 222), Sea_Shell_3(205, 197, 191),
    Sea_Shell_4(139, 134, 130), Brown_Sandy(244, 164, 96), Sienna_Raw(199, 97, 20), Chocolate(210, 105, 30),
    Chocolate_2(255, 127, 36), Chocolate_3(238, 118, 33), Chocolate_4(205, 102, 29), Chocolate_5(139, 69, 19),
    Black_Ivory(41, 36, 33);
*/
    private float r, g, b;

    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void bind() {
        glColor4f(r, g, b, 1);
    }

    public void bind(float opacity) {
        glColor4f(r, g, b, opacity);
    }
    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

    public static Color random(){
        return new Color((float)Math.random() * 255, (float)Math.random() * 255, (float) Math.random());
    }
}
