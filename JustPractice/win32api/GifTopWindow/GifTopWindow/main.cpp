#include <windows.h>
#include <gdiplus.h>
#include <atlbase.h>
#pragma comment(lib,"gdiplus.lib")

/*  GDI+ startup token */
ULONG_PTR gdiplusStartupToken;

/*  Declare Windows procedure  */
LRESULT CALLBACK WindowProcedure(HWND, UINT, WPARAM, LPARAM);

// UpdateLayeredWindow Defination
typedef BOOL(*UPDATELAYEREDWINDOWFUNCTION)(HWND, HDC, POINT*, SIZE*, HDC, POINT*, COLORREF, BLENDFUNCTION*, DWORD);

/*  Make the class name into a global variable  */
#define ID_TIMER 1
#define DELAY_IMAGE_DEFAULT 3
wchar_t szClassName[] = L"LOLIS";
RECT wndRect;
SIZE wndSize;
BLENDFUNCTION blendFunction;
CAutoPtr<Gdiplus::Image> images[32];

int countImageId;
const int maxImageId = 31;
int delayImage = DELAY_IMAGE_DEFAULT;

int WINAPI WinMain(HINSTANCE hThisInstance,
	HINSTANCE hPrevInstance,
	LPSTR lpszArgument,
	int nCmdShow)
{

	/**/
	Gdiplus::GdiplusStartupInput gdiInput;
	Gdiplus::GdiplusStartup(&gdiplusStartupToken, &gdiInput, NULL);
	/**/
	HWND hwnd;               /* This is the handle for our window */
	MSG messages;            /* Here messages to the application are saved */
	WNDCLASSEX wincl;        /* Data structure for the windowclass */

							 /* The Window structure */
	wincl.hInstance = hThisInstance;
	wincl.lpszClassName = szClassName;//+-69+
	wincl.lpfnWndProc = WindowProcedure;      /* This function is called by windows */
	wincl.style = CS_DBLCLKS;                 /* Catch double-clicks */
	wincl.cbSize = sizeof(WNDCLASSEX);

	/* Use default icon and mouse-pointer */
	wincl.hIcon = LoadIcon(NULL, IDI_APPLICATION);
	wincl.hIconSm = LoadIcon(NULL, IDI_APPLICATION);
	wincl.hCursor = LoadCursor(NULL, IDC_ARROW);
	wincl.lpszMenuName = NULL;                 /* No menu */
	wincl.cbClsExtra = 0;                      /* No extra bytes after the window class */
	wincl.cbWndExtra = 0;                      /* structure or the window instance */
											   /* Use Windows's default colour as the background of the window */
	wincl.hbrBackground = (HBRUSH)COLOR_BACKGROUND;

	/* Register the window class, and if it fails quit the program */
	if (!RegisterClassEx(&wincl))
		return 0;

	/* The class is registered, let's create the program*/
	hwnd = CreateWindowEx(
		WS_EX_LAYERED | WS_EX_TOPMOST | WS_EX_TOOLWINDOW,                   /* Extended possibilites for variation */
		szClassName,         /* Classname */
		L"LOLIS",       /* Title Text */
		WS_OVERLAPPEDWINDOW, /* default window */
		CW_USEDEFAULT,       /* Windows decides the position */
		CW_USEDEFAULT,       /* where the window ends up on the screen */
		440,                 /* The programs width */
		282,                 /* and height in pixels */
		HWND_DESKTOP,        /* The window is a child-window to desktop */
		NULL,                /* No menu */
		hThisInstance,       /* Program Instance handler */
		NULL                 /* No Window Creation data */
		);

	/* Make the window visible on the screen */
	ShowWindow(hwnd, nCmdShow);
	LONG style = ::GetWindowLong(hwnd, GWL_STYLE);
	if (style&WS_CAPTION)
		style ^= WS_CAPTION;
	if (style&WS_THICKFRAME)
		style ^= WS_THICKFRAME;
	if (style&WS_SYSMENU)
		style ^= WS_SYSMENU;
	::SetWindowLong(hwnd, GWL_STYLE, style);

	style = ::GetWindowLong(hwnd, GWL_EXSTYLE);
	if (style&WS_EX_APPWINDOW)
		style ^= WS_EX_APPWINDOW;
	::SetWindowLong(hwnd, GWL_EXSTYLE, style);

	/********************************************
	*   step 1.
	*   Using Gdiplus to load the image
	********************************************/
	
	::GetWindowRect(hwnd, &wndRect);

	// load images
	images[0].Attach(new Gdiplus::Bitmap(L"lolis/0.png"));
	images[1].Attach(new Gdiplus::Bitmap(L"lolis/1.png"));
	images[2].Attach(new Gdiplus::Bitmap(L"lolis/2.png"));
	images[3].Attach(new Gdiplus::Bitmap(L"lolis/3.png"));
	images[4].Attach(new Gdiplus::Bitmap(L"lolis/4.png"));
	images[5].Attach(new Gdiplus::Bitmap(L"lolis/5.png"));
	images[6].Attach(new Gdiplus::Bitmap(L"lolis/6.png"));
	images[7].Attach(new Gdiplus::Bitmap(L"lolis/7.png"));
	images[8].Attach(new Gdiplus::Bitmap(L"lolis/8.png"));
	images[9].Attach(new Gdiplus::Bitmap(L"lolis/9.png"));
	images[10].Attach(new Gdiplus::Bitmap(L"lolis/10.png"));
	images[11].Attach(new Gdiplus::Bitmap(L"lolis/11.png"));
	images[12].Attach(new Gdiplus::Bitmap(L"lolis/12.png"));
	images[13].Attach(new Gdiplus::Bitmap(L"lolis/13.png"));
	images[14].Attach(new Gdiplus::Bitmap(L"lolis/14.png"));
	images[15].Attach(new Gdiplus::Bitmap(L"lolis/15.png"));
	images[16].Attach(new Gdiplus::Bitmap(L"lolis/16.png"));
	images[17].Attach(new Gdiplus::Bitmap(L"lolis/17.png"));
	images[18].Attach(new Gdiplus::Bitmap(L"lolis/18.png"));
	images[19].Attach(new Gdiplus::Bitmap(L"lolis/19.png"));
	images[20].Attach(new Gdiplus::Bitmap(L"lolis/20.png"));
	images[21].Attach(new Gdiplus::Bitmap(L"lolis/21.png"));
	images[22].Attach(new Gdiplus::Bitmap(L"lolis/22.png"));
	images[23].Attach(new Gdiplus::Bitmap(L"lolis/23.png"));
	images[24].Attach(new Gdiplus::Bitmap(L"lolis/24.png"));
	images[25].Attach(new Gdiplus::Bitmap(L"lolis/25.png"));
	images[26].Attach(new Gdiplus::Bitmap(L"lolis/26.png"));
	images[27].Attach(new Gdiplus::Bitmap(L"lolis/27.png"));
	images[28].Attach(new Gdiplus::Bitmap(L"lolis/28.png"));
	images[29].Attach(new Gdiplus::Bitmap(L"lolis/29.png"));
	images[30].Attach(new Gdiplus::Bitmap(L"lolis/30.png"));
	images[31].Attach(new Gdiplus::Bitmap(L"lolis/31.png"));

	/*********************************************
	*   step 3.
	*   Use UpdateLayeredWindow to Draw the Window
	*
	*********************************************/
	blendFunction.AlphaFormat = AC_SRC_ALPHA;
	blendFunction.BlendFlags = 0;
	blendFunction.BlendOp = AC_SRC_OVER;
	blendFunction.SourceConstantAlpha = 255;


	/* Run the message loop. It will run until GetMessage() returns 0 */
	while (GetMessage(&messages, NULL, 0, 0))
	{
		/* Translate virtual-key messages into character messages */
		TranslateMessage(&messages);
		/* Send message to WindowProcedure */
		DispatchMessage(&messages);
	}
	Gdiplus::GdiplusShutdown(gdiplusStartupToken);
	/* The program return-value is 0 - The value that PostQuitMessage() gave */
	return messages.wParam;
}


/*  This function is called by the Windows function DispatchMessage()  */

LRESULT CALLBACK WindowProcedure(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam)
{
	HDC hdc;
	HDC memDC;
	HBITMAP memBitmap;
	CAutoPtr<Gdiplus::Graphics> graphics;


	switch (message)                  /* handle the messages */
	{
	case WM_TIMER:
	{
		hdc = GetDC(hWnd);
		memDC = CreateCompatibleDC(hdc);
		memBitmap = CreateCompatibleBitmap(hdc, wndSize.cx, wndSize.cy);

		GetWindowRect(hWnd, &wndRect);
		wndSize = { wndRect.right - wndRect.left,wndRect.bottom - wndRect.top };
		POINT ptTemp = { wndRect.left, wndRect.top };
		POINT ptSrc = { 0, 0 };

		SelectObject(memDC, memBitmap);
		graphics.Free();
		graphics.Attach(new Gdiplus::Graphics(memDC));

		if (countImageId > maxImageId) countImageId = 0;
		if (countImageId == 30 && delayImage) delayImage--;
		else {
			delayImage = DELAY_IMAGE_DEFAULT;
			(*graphics).DrawImage(images[countImageId], 0, 0, wndSize.cx, wndSize.cy);
			countImageId += 1;
			UpdateLayeredWindow(hWnd, hdc, &ptTemp, &wndSize, memDC, &ptSrc, 0, &blendFunction, 2);
		}

		DeleteObject(memBitmap);
		DeleteDC(memDC);
		ReleaseDC(hWnd, hdc);
	}
		return 0;
	case WM_PAINT:
		
		break;

	case WM_CREATE:
		countImageId = 0;
		SetTimer(hWnd, ID_TIMER, 100, NULL);
		break;
	case WM_DESTROY:
		KillTimer(hWnd, ID_TIMER);
		PostQuitMessage(0);       /* send a WM_QUIT to the message queue */
		break;
	case WM_LBUTTONDOWN:
		SendMessage(hWnd, WM_NCLBUTTONDOWN, HTCAPTION, 0);
		break;
	case WM_KEYDOWN:
		switch (wParam)
		{
		case VK_ESCAPE:
			SendMessage(hWnd, WM_CLOSE, 0, 0);
			break;
		}
		return 0;
	default:                      /* for messages that we don't deal with */
		return DefWindowProc(hWnd, message, wParam, lParam);
	}
	return 0;
}
