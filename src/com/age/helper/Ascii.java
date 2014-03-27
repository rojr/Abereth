package com.age.helper;

/**
 * Created by apex on 27/03/14.
 */
public class Ascii {

    public static Character[] asciiList = new Character[128];

    public static char SPACE = set(32, ' '),
                       EXCLAMATION_MARK = set(33, '!'),
                       QUOTATION_MAKR = set(34, '"'),
                       HASH = set(35, '#'),
                       DOLLAR_SIGN = set(36, '$'),
                       PERCENT = set(37, '%'),
                       AMPERSAND = set(38, '&'),
                       APOSTROPHE = set(39, '\''),
                       OPEN_ROUND_BRACKET = set(40, '('),
                       CLOSED_ROUND_BRACKET = set(41, ')'),
                       ASTERISK = set(42, '*'),
                       PLUS = set(43, '+'),
                       COMMA = set(44, ','),
                       MINUS = set(45, '-'),
                       PERIOD = set(46, '.'),
                       SLASH = set(47, '/'),
                       ZERO = set(48, '0'),
                       ONE = set(49, '1'),
                       TWO = set(50, '2'),
                       THREE = set(51, '3'),
                       FOUR = set(52, '4'),
                       FIVE = set(53, '5'),
                       SIX = set(54, '6'),
                       SEVEN = set(55, '7'),
                       EIGHT = set(56, '8'),
                       NINE = set(57, '9'),
                       COLON = set(58, ':'),
                       SEMI_COLON = set(59, ';'),
                       LESS_THAN = set(60, '<'),
                       EQUALS = set(61, '='),
                       GREATER_THAN = set(62, '>'),
                       QUESTION_MARK = set(63, '?'),
                       AT_SIGN = set(64, '@'),
                       A = set(65, 'A'),
                       B = set(66, 'B'),
                       C = set(67, 'C'),
                       D = set(68, 'D'),
                       E = set(69, 'E'),
                       F = set(70, 'F'),
                       G = set(71, 'G'),
                       H = set(72, 'H'),
                       I = set(73, 'I'),
                       J = set(74, 'J'),
                       K = set(75, 'K'),
                       L = set(76, 'L'),
                       M = set(77, 'M'),
                       N = set(78, 'N'),
                       O = set(79, 'O'),
                       P = set(80, 'P'),
                       Q = set(81, 'Q'),
                       R = set(82, 'R'),
                       S = set(83, 'S'),
                       T = set(84, 'T'),
                       U = set(85, 'U'),
                       V = set(86, 'V'),
                       W = set(87, 'W'),
                       X = set(88, 'X'),
                       Y = set(89, 'Y'),
                       Z = set(90, 'Z'),
                       OPEN_SQUARE_BRACKET = set(91, '['),
                       BACKSLASH = set(92, '\\'),
                       CLOSED_SQUARE_BRACKET = set(93, ']'),
                       CARET = set(94, '^'),
                       UNDERSCORE = set(95, '_'),
                       GRAVE = set(96, '`'),
                       a = set(97, 'a'),
                       b = set(98, 'b'),
                       c = set(99, 'c'),
                       d = set(100, 'd'),
                       e = set(101, 'e'),
                       f = set(102, 'f'),
                       g = set(103, 'g'),
                       h = set(104, 'h'),
                       i = set(105, 'i'),
                       j = set(106, 'j'),
                       k = set(107, 'k'),
                       l = set(108, 'l'),
                       m = set(109, 'm'),
                       n = set(110, 'n'),
                       o = set(111, 'o'),
                       p = set(112, 'p'),
                       q = set(113, 'q'),
                       r = set(114, 'r'),
                       s = set(115, 's'),
                       t = set(116, 't'),
                       u = set(117, 'u'),
                       v = set(118, 'v'),
                       w = set(119, 'w'),
                       x = set(120, 'x'),
                       y = set(121, 'y'),
                       z = set(122, 'z'),
                       OPEN_CURLY_BRACKET = set(123, '{'),
                       PIPE = set(124, '|'),
                       CLOSED_CURLY_BRACKET = set(125, '}'),
                       TILDE = set(126, '~');

    public static Character set(int index, char c){
        if(index < asciiList.length)
            asciiList[index] = c;
        return c;
    }

    public static String asciiToNumber(String text){
        String tmp = "";
        for(char c : text.toCharArray()){
            tmp += charToInt(c) + " ";
        }
        return tmp;
    }

    public static String numberToAscii(String text){
        String tmp = "";
        return tmp;
    }

    public static int charToInt(char c){
        for(int i = 0; i < asciiList.length; i++){
            if(asciiList[i] != null){
                if(asciiList[i] == c){
                    return i;
                }
            }
        }
        return 0;
    }

    public static char intToChar(int number) throws ArrayIndexOutOfBoundsException{
        if(number < asciiList.length){
            if(asciiList[number] != null){
                return asciiList[number];
            }
        }else{
            throw new ArrayIndexOutOfBoundsException();
        }
        return ' ';
    }
}
