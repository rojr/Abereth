package com.gmail.robmadeyou.Input;

public class Keyboard {
	
	public static boolean isKeyPressed(Key key){
		while(org.lwjgl.input.Keyboard.next()){
			if(org.lwjgl.input.Keyboard.getEventKeyState()){
				if(org.lwjgl.input.Keyboard.isKeyDown(key.getValue())){
					return true;
				}
			}
		}
		return false;
	}
	public static boolean isKeyDown(Key key){
		if(org.lwjgl.input.Keyboard.isKeyDown((key.getValue()))){
			return true;
		}
		return false;
	}
	public static enum Key{
		
		UpArrow(org.lwjgl.input.Keyboard.KEY_UP),
		DownArrow(org.lwjgl.input.Keyboard.KEY_DOWN),
		LeftArrow(org.lwjgl.input.Keyboard.KEY_LEFT),
		RightArrow(org.lwjgl.input.Keyboard.KEY_RIGHT),
		
		Zero(org.lwjgl.input.Keyboard.KEY_0),
		One(org.lwjgl.input.Keyboard.KEY_1),
		Two(org.lwjgl.input.Keyboard.KEY_2),
		Three(org.lwjgl.input.Keyboard.KEY_3),
		Four(org.lwjgl.input.Keyboard.KEY_4),
		Five(org.lwjgl.input.Keyboard.KEY_5),
		Six(org.lwjgl.input.Keyboard.KEY_6),
		Seven(org.lwjgl.input.Keyboard.KEY_7),
		Eight(org.lwjgl.input.Keyboard.KEY_8),
		Nine(org.lwjgl.input.Keyboard.KEY_9),
		
		Numpad_Comma(org.lwjgl.input.Keyboard.KEY_NUMPADCOMMA),
		Numpad_Enter(org.lwjgl.input.Keyboard.KEY_NUMPADENTER),
		Numpad_Equals(org.lwjgl.input.Keyboard.KEY_NUMPADEQUALS),
		NumLock(org.lwjgl.input.Keyboard.KEY_NUMLOCK),
		Numpad_Zero(org.lwjgl.input.Keyboard.KEY_NUMPAD0),
		Numpad_One(org.lwjgl.input.Keyboard.KEY_NUMPAD1),
		Numpad_Two(org.lwjgl.input.Keyboard.KEY_NUMPAD2),
		Numpad_Three(org.lwjgl.input.Keyboard.KEY_NUMPAD3),
		Numpad_Four(org.lwjgl.input.Keyboard.KEY_NUMPAD4),
		Numpad_Five(org.lwjgl.input.Keyboard.KEY_NUMPAD5),
		Numpad_Six(org.lwjgl.input.Keyboard.KEY_NUMPAD6),
		Numpad_Seven(org.lwjgl.input.Keyboard.KEY_NUMPAD7),
		Numpad_Eight(org.lwjgl.input.Keyboard.KEY_NUMPAD8),
		Numpad_Nine(org.lwjgl.input.Keyboard.KEY_NUMPAD9),
		
		F1(org.lwjgl.input.Keyboard.KEY_F1),
		F2(org.lwjgl.input.Keyboard.KEY_F2),
		F3(org.lwjgl.input.Keyboard.KEY_F3),
		F4(org.lwjgl.input.Keyboard.KEY_F4),
		F5(org.lwjgl.input.Keyboard.KEY_F5),
		F6(org.lwjgl.input.Keyboard.KEY_F6),
		F7(org.lwjgl.input.Keyboard.KEY_F7),
		F8(org.lwjgl.input.Keyboard.KEY_F8),
		F9(org.lwjgl.input.Keyboard.KEY_F9),
		F10(org.lwjgl.input.Keyboard.KEY_F10),
		F11(org.lwjgl.input.Keyboard.KEY_F11),
		F12(org.lwjgl.input.Keyboard.KEY_F12),
		F13(org.lwjgl.input.Keyboard.KEY_F13),
		F14(org.lwjgl.input.Keyboard.KEY_F14),
		F15(org.lwjgl.input.Keyboard.KEY_F15),
		
		Q(org.lwjgl.input.Keyboard.KEY_Q),
		W(org.lwjgl.input.Keyboard.KEY_W),
		E(org.lwjgl.input.Keyboard.KEY_E),
		R(org.lwjgl.input.Keyboard.KEY_R),
		T(org.lwjgl.input.Keyboard.KEY_T),
		Y(org.lwjgl.input.Keyboard.KEY_Y),
		U(org.lwjgl.input.Keyboard.KEY_U),
		I(org.lwjgl.input.Keyboard.KEY_I),
		O(org.lwjgl.input.Keyboard.KEY_O),
		P(org.lwjgl.input.Keyboard.KEY_P),
		A(org.lwjgl.input.Keyboard.KEY_A),
		S(org.lwjgl.input.Keyboard.KEY_S),
		D(org.lwjgl.input.Keyboard.KEY_D),
		F(org.lwjgl.input.Keyboard.KEY_F),
		G(org.lwjgl.input.Keyboard.KEY_G),
		H(org.lwjgl.input.Keyboard.KEY_H),
		J(org.lwjgl.input.Keyboard.KEY_J),
		K(org.lwjgl.input.Keyboard.KEY_K),
		L(org.lwjgl.input.Keyboard.KEY_L),
		Z(org.lwjgl.input.Keyboard.KEY_Z),
		X(org.lwjgl.input.Keyboard.KEY_X),
		C(org.lwjgl.input.Keyboard.KEY_C),
		V(org.lwjgl.input.Keyboard.KEY_V),
		B(org.lwjgl.input.Keyboard.KEY_B),
		N(org.lwjgl.input.Keyboard.KEY_N),
		M(org.lwjgl.input.Keyboard.KEY_M),
		
		Add(org.lwjgl.input.Keyboard.KEY_ADD),
		Apostrophe(org.lwjgl.input.Keyboard.KEY_APOSTROPHE),
		Apps(org.lwjgl.input.Keyboard.KEY_APPS),
		At(org.lwjgl.input.Keyboard.KEY_AT),
		Ax(org.lwjgl.input.Keyboard.KEY_AX),
		Back(org.lwjgl.input.Keyboard.KEY_BACK),
		BackSlash(org.lwjgl.input.Keyboard.KEY_BACKSLASH),
		Captial(org.lwjgl.input.Keyboard.KEY_CAPITAL),
		Circumflex(org.lwjgl.input.Keyboard.KEY_CIRCUMFLEX),
		Colon(org.lwjgl.input.Keyboard.KEY_COLON),
		Comma(org.lwjgl.input.Keyboard.KEY_COMMA),
		Convert(org.lwjgl.input.Keyboard.KEY_CONVERT),
		Decimal(org.lwjgl.input.Keyboard.KEY_DECIMAL),
		Delete(org.lwjgl.input.Keyboard.KEY_DELETE),
		Divide(org.lwjgl.input.Keyboard.KEY_DIVIDE),
		End(org.lwjgl.input.Keyboard.KEY_END),
		Equals(org.lwjgl.input.Keyboard.KEY_EQUALS),
		Escape(org.lwjgl.input.Keyboard.KEY_ESCAPE),
		Grave(org.lwjgl.input.Keyboard.KEY_GRAVE),
		Home(org.lwjgl.input.Keyboard.KEY_HOME),
		Insert(org.lwjgl.input.Keyboard.KEY_INSERT),
		Kana(org.lwjgl.input.Keyboard.KEY_KANA),
		Kanji(org.lwjgl.input.Keyboard.KEY_KANJI),
		LBracket(org.lwjgl.input.Keyboard.KEY_LBRACKET),
		LControl(org.lwjgl.input.Keyboard.KEY_LCONTROL),
		LMenu(org.lwjgl.input.Keyboard.KEY_LMENU),
		LMeta(org.lwjgl.input.Keyboard.KEY_LMETA),
		LShift(org.lwjgl.input.Keyboard.KEY_LSHIFT),
		LeftWindowsButton(org.lwjgl.input.Keyboard.KEY_LWIN),
		Minus(org.lwjgl.input.Keyboard.KEY_MINUS),
		Multiply(org.lwjgl.input.Keyboard.KEY_MULTIPLY),
		Next(org.lwjgl.input.Keyboard.KEY_NEXT),
		NoConvert(org.lwjgl.input.Keyboard.KEY_NOCONVERT),
		None(org.lwjgl.input.Keyboard.KEY_NONE),
		Pause(org.lwjgl.input.Keyboard.KEY_PAUSE),
		Period(org.lwjgl.input.Keyboard.KEY_PERIOD),
		Power(org.lwjgl.input.Keyboard.KEY_POWER),
		Prior(org.lwjgl.input.Keyboard.KEY_PRIOR),
		RBracket(org.lwjgl.input.Keyboard.KEY_RBRACKET),
		RControl(org.lwjgl.input.Keyboard.KEY_RCONTROL),
		Return(org.lwjgl.input.Keyboard.KEY_RETURN),
		RMenu(org.lwjgl.input.Keyboard.KEY_RMENU),
		RMeta(org.lwjgl.input.Keyboard.KEY_RMETA),
		RShift(org.lwjgl.input.Keyboard.KEY_RSHIFT),
		RightWindowsButton(org.lwjgl.input.Keyboard.KEY_RWIN),
		Scroll(org.lwjgl.input.Keyboard.KEY_SCROLL),
		Semicolon(org.lwjgl.input.Keyboard.KEY_SEMICOLON),
		Slash(org.lwjgl.input.Keyboard.KEY_SLASH),
		Sleep(org.lwjgl.input.Keyboard.KEY_SLEEP),
		Space(org.lwjgl.input.Keyboard.KEY_SPACE),
		Stop(org.lwjgl.input.Keyboard.KEY_STOP),
		Subtract(org.lwjgl.input.Keyboard.KEY_SUBTRACT),
		SYSRQ(org.lwjgl.input.Keyboard.KEY_SYSRQ),
		Tab(org.lwjgl.input.Keyboard.KEY_TAB),
		Underline(org.lwjgl.input.Keyboard.KEY_UNDERLINE),
		Unlabeled(org.lwjgl.input.Keyboard.KEY_UNLABELED),
		Yen(org.lwjgl.input.Keyboard.KEY_YEN)
		;
		
		private int number;
		private boolean isDown = false;
		Key(int number){
			this.number = number;
		}
		public int getValue(){
			return number;
		}
	}
}
