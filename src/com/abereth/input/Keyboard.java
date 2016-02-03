package com.abereth.input;

import com.abereth.G;

/**
 * Created by jeremiah on 17/11/2014.
 */
public class Keyboard
{

	public static boolean isKeyPressed( Key key )
	{
		isKeyDown( key );
		return key.isPressed;
	}

	public static boolean isKeyDown( Key key )
	{
		if( org.lwjgl.input.Keyboard.isKeyDown( ( key.getValue() ) ) )
		{
			if( key.isPressed && !key.isReleasedAfterPressed )
			{
				key.setIsPressed( false );
			}
			if( key.isReleasedAfterPressed && !key.isPressed )
			{
				key.setIsPressed( true );

				key.setIsReleasedAfterPressed( false );
			}
			return true;
		}
		key.setIsPressed( false );
		key.setIsReleasedAfterPressed( true );
		return false;
	}

	public static String getPressedCharacter()
	{
		while ( org.lwjgl.input.Keyboard.next() )
		{
			if( org.lwjgl.input.Keyboard.getEventKeyState() )
			{
				if( org.lwjgl.input.Keyboard.isKeyDown( org.lwjgl.input.Keyboard.getEventKey() ) )
				{
					for ( int i = 0; i < G.characters.length; i++ )
					{
						if( org.lwjgl.input.Keyboard.getEventCharacter() == G.characters[ i ] )
						{
							System.out.println( org.lwjgl.input.Keyboard.getEventCharacter() );
							return org.lwjgl.input.Keyboard.getEventCharacter() + "";
						}
					}
					if( org.lwjgl.input.Keyboard.isKeyDown( org.lwjgl.input.Keyboard.KEY_RETURN ) )
					{
						System.out.println( "enter" );
						return "\n";
					}
					else if( org.lwjgl.input.Keyboard.isKeyDown( org.lwjgl.input.Keyboard.KEY_SPACE ) )
					{
						System.out.println( "space" );
						return " ";
					}
					else if( org.lwjgl.input.Keyboard.isKeyDown( org.lwjgl.input.Keyboard.KEY_BACK ))
					{
						System.out.println( "backspace" );
						return "bckspc";
					}
				}
			}
		}
		return "";
	}

	public static enum Key
	{
		Up( org.lwjgl.input.Keyboard.KEY_UP, false, false ),
		Down( org.lwjgl.input.Keyboard.KEY_DOWN, false, false ),
		Left( org.lwjgl.input.Keyboard.KEY_LEFT, false, false ),
		Right( org.lwjgl.input.Keyboard.KEY_RIGHT, false, false ),

		Zero( org.lwjgl.input.Keyboard.KEY_0, false, false ),
		One( org.lwjgl.input.Keyboard.KEY_1, false, false ),
		Two( org.lwjgl.input.Keyboard.KEY_2, false, false ),
		Three( org.lwjgl.input.Keyboard.KEY_3, false, false ),
		Four( org.lwjgl.input.Keyboard.KEY_4, false, false ),
		Five( org.lwjgl.input.Keyboard.KEY_5, false, false ),
		Six( org.lwjgl.input.Keyboard.KEY_6, false, false ),
		Seven( org.lwjgl.input.Keyboard.KEY_7, false, false ),
		Eight( org.lwjgl.input.Keyboard.KEY_8, false, false ),
		Nine( org.lwjgl.input.Keyboard.KEY_9, false, false ),

		Numpad_Comma( org.lwjgl.input.Keyboard.KEY_NUMPADCOMMA, false, false ), 
		Numpad_Enter( org.lwjgl.input.Keyboard.KEY_NUMPADENTER, false, false ),
		Numpad_Equals( org.lwjgl.input.Keyboard.KEY_NUMPADEQUALS, false, false ),
        NumLock( org.lwjgl.input.Keyboard.KEY_NUMLOCK, false, false ),
        Numpad_Zero( org.lwjgl.input.Keyboard.KEY_NUMPAD0, false, false ),
        Numpad_One( org.lwjgl.input.Keyboard.KEY_NUMPAD1, false, false ),
        Numpad_Two( org.lwjgl.input.Keyboard.KEY_NUMPAD2, false, false ),
        Numpad_Three( org.lwjgl.input.Keyboard.KEY_NUMPAD3, false, false ),
        Numpad_Four( org.lwjgl.input.Keyboard.KEY_NUMPAD4, false, false ),
        Numpad_Five( org.lwjgl.input.Keyboard.KEY_NUMPAD5, false, false ), 
        Numpad_Six( org.lwjgl.input.Keyboard.KEY_NUMPAD6, false, false ),
        Numpad_Seven( org.lwjgl.input.Keyboard.KEY_NUMPAD7, false, false ),
        Numpad_Eight( org.lwjgl.input.Keyboard.KEY_NUMPAD8, false, false ),
        Numpad_Nine( org.lwjgl.input.Keyboard.KEY_NUMPAD9, false, false ),

		F1( org.lwjgl.input.Keyboard.KEY_F1, false, false ),
		F2( org.lwjgl.input.Keyboard.KEY_F2, false, false ),
		F3( org.lwjgl.input.Keyboard.KEY_F3, false, false ),
		F4( org.lwjgl.input.Keyboard.KEY_F4, false, false ),
		F5( org.lwjgl.input.Keyboard.KEY_F5, false, false ),
		F6( org.lwjgl.input.Keyboard.KEY_F6, false, false ),
		F7( org.lwjgl.input.Keyboard.KEY_F7, false, false ),
		F8( org.lwjgl.input.Keyboard.KEY_F8, false, false ),
		F9( org.lwjgl.input.Keyboard.KEY_F9, false, false ),
		F10( org.lwjgl.input.Keyboard.KEY_F10, false, false ),
		F11( org.lwjgl.input.Keyboard.KEY_F11, false, false ),
		F12( org.lwjgl.input.Keyboard.KEY_F12, false, false ),
		F13( org.lwjgl.input.Keyboard.KEY_F13, false, false ),
		F14( org.lwjgl.input.Keyboard.KEY_F14, false, false ),
		F15( org.lwjgl.input.Keyboard.KEY_F15, false, false ),

		Q( org.lwjgl.input.Keyboard.KEY_Q, false, false ), W(
			org.lwjgl.input.Keyboard.KEY_W, false, false ), E(
			org.lwjgl.input.Keyboard.KEY_E, false, false ), R(
			org.lwjgl.input.Keyboard.KEY_R, false, false ), T(
			org.lwjgl.input.Keyboard.KEY_T, false, false ), Y(
			org.lwjgl.input.Keyboard.KEY_Y, false, false ), U(
			org.lwjgl.input.Keyboard.KEY_U, false, false ), I(
			org.lwjgl.input.Keyboard.KEY_I, false, false ), O(
			org.lwjgl.input.Keyboard.KEY_O, false, false ), P(
			org.lwjgl.input.Keyboard.KEY_P, false, false ), A(
			org.lwjgl.input.Keyboard.KEY_A, false, false ), S(
			org.lwjgl.input.Keyboard.KEY_S, false, false ), D(
			org.lwjgl.input.Keyboard.KEY_D, false, false ), F(
			org.lwjgl.input.Keyboard.KEY_F, false, false ), G(
			org.lwjgl.input.Keyboard.KEY_G, false, false ), H(
			org.lwjgl.input.Keyboard.KEY_H, false, false ), J(
			org.lwjgl.input.Keyboard.KEY_J, false, false ), K(
			org.lwjgl.input.Keyboard.KEY_K, false, false ), L(
			org.lwjgl.input.Keyboard.KEY_L, false, false ), Z(
			org.lwjgl.input.Keyboard.KEY_Z, false, false ), X(
			org.lwjgl.input.Keyboard.KEY_X, false, false ), C(
			org.lwjgl.input.Keyboard.KEY_C, false, false ), V(
			org.lwjgl.input.Keyboard.KEY_V, false, false ), B(
			org.lwjgl.input.Keyboard.KEY_B, false, false ), N(
			org.lwjgl.input.Keyboard.KEY_N, false, false ), M(
			org.lwjgl.input.Keyboard.KEY_M, false, false ),

		Add( org.lwjgl.input.Keyboard.KEY_ADD, false, false ),
        Apostrophe( org.lwjgl.input.Keyboard.KEY_APOSTROPHE, false, false ),
        Apps( org.lwjgl.input.Keyboard.KEY_APPS, false, false ),
        At( org.lwjgl.input.Keyboard.KEY_AT, false, false ),
        Ax( org.lwjgl.input.Keyboard.KEY_AX, false, false ),
        Back( org.lwjgl.input.Keyboard.KEY_BACK, false, false ),
        BackSlash(org.lwjgl.input.Keyboard.KEY_BACKSLASH, false, false ),
        Captial(org.lwjgl.input.Keyboard.KEY_CAPITAL, false, false ),
        Circumflex(org.lwjgl.input.Keyboard.KEY_CIRCUMFLEX, false, false ),
        Colon(org.lwjgl.input.Keyboard.KEY_COLON, false, false ),
        Comma(org.lwjgl.input.Keyboard.KEY_COMMA, false, false ),
        Convert(org.lwjgl.input.Keyboard.KEY_CONVERT, false, false ),
        Decimal(org.lwjgl.input.Keyboard.KEY_DECIMAL, false, false ),
        Delete(org.lwjgl.input.Keyboard.KEY_DELETE, false, false ),
        Divide(org.lwjgl.input.Keyboard.KEY_DIVIDE, false, false ),
        End(org.lwjgl.input.Keyboard.KEY_END, false, false ),
        Equals(org.lwjgl.input.Keyboard.KEY_EQUALS, false, false ),
        Escape(org.lwjgl.input.Keyboard.KEY_ESCAPE, false, false ),
        Grave(org.lwjgl.input.Keyboard.KEY_GRAVE, false, false ),
        Home(org.lwjgl.input.Keyboard.KEY_HOME, false, false ),
        Insert(org.lwjgl.input.Keyboard.KEY_INSERT, false, false ),
        Kana(org.lwjgl.input.Keyboard.KEY_KANA, false, false ),
        Kanji(org.lwjgl.input.Keyboard.KEY_KANJI, false, false ),
        LBracket(org.lwjgl.input.Keyboard.KEY_LBRACKET, false, false ),
        LControl(org.lwjgl.input.Keyboard.KEY_LCONTROL, false, false ),
        LMenu(org.lwjgl.input.Keyboard.KEY_LMENU, false, false ),
        LMeta(org.lwjgl.input.Keyboard.KEY_LMETA, false, false ),
        LShift(org.lwjgl.input.Keyboard.KEY_LSHIFT, false, false ),
        LeftWindowsButton(org.lwjgl.input.Keyboard.KEY_LWIN, false, false ),
        Minus(org.lwjgl.input.Keyboard.KEY_MINUS, false, false ),
        Multiply(org.lwjgl.input.Keyboard.KEY_MULTIPLY, false, false ),
        Next(org.lwjgl.input.Keyboard.KEY_NEXT, false, false ),
        NoConvert(org.lwjgl.input.Keyboard.KEY_NOCONVERT, false, false ),
        None(org.lwjgl.input.Keyboard.KEY_NONE, false, false ),
        Pause(org.lwjgl.input.Keyboard.KEY_PAUSE, false, false ),
        Period(org.lwjgl.input.Keyboard.KEY_PERIOD, false, false ),
        Power(org.lwjgl.input.Keyboard.KEY_POWER, false, false ),
        Prior(org.lwjgl.input.Keyboard.KEY_PRIOR, false, false ),
        RBracket(org.lwjgl.input.Keyboard.KEY_RBRACKET, false, false ),
        RControl(org.lwjgl.input.Keyboard.KEY_RCONTROL, false, false ),
        Return(org.lwjgl.input.Keyboard.KEY_RETURN, false, false ),
        RMenu(org.lwjgl.input.Keyboard.KEY_RMENU, false, false ),
        RMeta(org.lwjgl.input.Keyboard.KEY_RMETA, false, false ),
        RShift(org.lwjgl.input.Keyboard.KEY_RSHIFT, false, false ),
        RightWindowsButton(org.lwjgl.input.Keyboard.KEY_RWIN, false, false ),
        Scroll(org.lwjgl.input.Keyboard.KEY_SCROLL, false, false ),
        Semicolon(org.lwjgl.input.Keyboard.KEY_SEMICOLON, false, false ),
        Slash(org.lwjgl.input.Keyboard.KEY_SLASH, false, false ),
        Sleep(org.lwjgl.input.Keyboard.KEY_SLEEP, false, false ),
        Space(org.lwjgl.input.Keyboard.KEY_SPACE, false, false ),
        Stop(org.lwjgl.input.Keyboard.KEY_STOP, false, false ),
        Subtract(org.lwjgl.input.Keyboard.KEY_SUBTRACT, false, false ),
        SYSRQ(org.lwjgl.input.Keyboard.KEY_SYSRQ, false, false ),
        Tab(org.lwjgl.input.Keyboard.KEY_TAB, false, false ),
        Underline(org.lwjgl.input.Keyboard.KEY_UNDERLINE, false, false ),
        Unlabeled(org.lwjgl.input.Keyboard.KEY_UNLABELED, false, false ),
        Yen(org.lwjgl.input.Keyboard.KEY_YEN, false, false );

		private int number;
		private boolean isReleasedAfterPressed;
		private boolean isPressed;

		Key( int number, boolean isPressed, boolean isReleasedAfterPressed )
		{
			this.number = number;
			this.isReleasedAfterPressed = isReleasedAfterPressed;
			this.isPressed = isPressed;
		}

		public int getValue()
		{
			return number;
		}

		public boolean isDown()
		{
			return isReleasedAfterPressed;
		}

		public boolean isPressed()
		{
			return isPressed;
		}

		public void setIsPressed( boolean args )
		{
			this.isPressed = args;
		}

		public void setIsReleasedAfterPressed( boolean args )
		{
			this.isReleasedAfterPressed = args;
		}
	}
}