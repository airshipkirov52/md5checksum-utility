package checksum.app;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CheckSumApplication {
	private static LocalResourceManager localResourceManager;
	private static Shell shlMdSha;

	public static final int WINDOW_WIDTH = 900;
	public static final int WINDOW_HEIGHT = 462;

	public static final int GROUP_X = 10;
	public static final int GROUP_WIDTH_00 = 865;
	public static final int GROUP_HEIGHT_00 = 277;

	public static final int GROUP_WIDTH_01 = 865;
	public static final int GROUP_HEIGHT_01 = 125;

	public static final int FIELD_X = 10;
	public static final int FIELD_Y = 20;
	public static final int FIELD_WIDTH = 845;
	public static final int FIELD_HEIGHT = 32;

	public static final int LABEL_X = 10;
	public static final int LABEL_Y = 10;
	public static final int LABEL_WIDTH = 50;
	public static final int LABEL_HEIGHT = 16;

	public static final int CHECKBOX_X = 70;
	public static final int CHECKBOX_Y = 10;
	public static final int CHECKBOX_WIDTH = 20;
	public static final int CHECKBOX_HEIGHT = 20;

	public static final int TEXT_X = 100;
	public static final int TEXT_Y = 4;
	public static final int TEXT_WIDTH = 600;
	public static final int TEXT_HEIGHT = 24;

	public static final int BTN_X = 710;
	public static final int BTN_Y = 4;
	public static final int BTN_WIDTH = 125;
	public static final int BTN_HEIGHT = 24;

	public static final int RADIO_Y = 0;
	public static final int RADIO_WIDTH = 80;
	public static final int RADIO_HEIGHT = 30;

	public static final int DEFAULT_COLOR = SWT.COLOR_WIDGET_BACKGROUND;

	public static final FontDescriptor DEFAULT_FONT = FontDescriptor.createFrom("Segoe UI", 10, SWT.NORMAL);
	public static final FontDescriptor BOLD_FONT = FontDescriptor.createFrom("Segoe UI", 10, SWT.BOLD);

	public static final String LABEL_TEXT_00 = "File";

	public static final String LABEL_TEXT_MD5 = "MD5";

	public static final String LABEL_TEXT_SHA1 = "SHA-1";

	public static final String LABEL_TEXT_SHA224 = "SHA-224";

	public static final String LABEL_TEXT_SHA256 = "SHA-256";

	public static final String LABEL_TEXT_SHA384 = "SHA-384";

	public static final String LABEL_TEXT_SHA512 = "SHA-512";

	public static final String LABEL_TEXT_HASH = "Hash";

	public static final String BTN_TEXT_BROWSE = "Browse";

	public static final String BTN_TEXT_CP_MD5 = "Copy MD5";

	public static final String BTN_TEXT_CP_SHA1 = "Copy SHA-1";

	public static final String BTN_TEXT_CP_SHA224 = "Copy SHA-224";

	public static final String BTN_TEXT_CP_SHA256 = "Copy SHA-256";

	public static final String BTN_TEXT_CP_SHA384 = "Copy SHA-384";

	public static final String BTN_TEXT_CP_SHA512 = "Copy SHA-512";

	public static final String BTN_TEXT_PASTE = "Paste";

	public static final String BTN_TEXT_VERIFY = "Verify";

	public static final String GROUP_TEXT_00 = "Generate Hash";

	public static final String GROUP_TEXT_01 = "Verify Hash";

	private static void createResourceManager() {
		localResourceManager = new LocalResourceManager(JFaceResources.getResources(), shlMdSha);
	}

	public static void main(String[] args) {
		Display display = Display.getDefault();
		shlMdSha = new Shell();
		createResourceManager();
		shlMdSha.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		shlMdSha.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		shlMdSha.setText("Checksums Utility Tool V1.0");
		Image icon = new Image(display, "D:\\ChecksumsToolV1.0\\icon_00.ico");
		shlMdSha.setImage(icon);
		
		Font font = localResourceManager.createFont(DEFAULT_FONT);
		Font boldfont = localResourceManager.createFont(BOLD_FONT);

		Group group_00 = new Group(shlMdSha, SWT.NONE);
		group_00.setText(GROUP_TEXT_00);
		group_00.setFont(font);
		group_00.setBounds(GROUP_X, 5, GROUP_WIDTH_00, GROUP_HEIGHT_00);
		group_00.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));

		// radio
		Composite composite_00 = new Composite(group_00, SWT.NONE);
		composite_00.setBounds(10, FIELD_Y, FIELD_WIDTH, FIELD_HEIGHT);
		composite_00.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));

		Button radio_file = new Button(composite_00, SWT.RADIO);
		radio_file.setBounds(100, 0, RADIO_WIDTH, RADIO_HEIGHT);
		radio_file.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		radio_file.setFont(font);
		radio_file.setText("File");
		radio_file.setSelection(true);

		Button radio_text = new Button(composite_00, SWT.RADIO);
		radio_text.setBounds(180, RADIO_Y, RADIO_WIDTH, RADIO_HEIGHT);
		radio_text.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		radio_text.setFont(font);
		radio_text.setText("Text");

		/** field 00 **/
		Composite composite_01 = new Composite(group_00, SWT.NONE);
		composite_01.setBounds(10, (FIELD_Y * 2) + 10, FIELD_WIDTH, FIELD_HEIGHT);
		composite_01.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));

		Label label_type = new Label(composite_01, SWT.NONE);
		label_type.setAlignment(SWT.RIGHT);
		label_type.setBounds(LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
		label_type.setFont(font);
		label_type.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		label_type.setText(LABEL_TEXT_00);

		ProgressBar progressBar_file = new ProgressBar(composite_01, SWT.NONE);
		progressBar_file.setBounds(TEXT_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT);
		progressBar_file.setMaximum(100);
		progressBar_file.setMinimum(0);
		progressBar_file.setVisible(true);

		Text text_00 = new Text(composite_01, SWT.BORDER);
		text_00.setBounds(TEXT_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT);
		text_00.setFont(font);
		text_00.setVisible(false);

		Button btn_browse = new Button(composite_01, SWT.NONE);
		btn_browse.setBounds(BTN_X, BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		btn_browse.setFont(font);
		btn_browse.setText(BTN_TEXT_BROWSE);

		/** md5 **/
		Composite composite_md5 = new Composite(group_00, SWT.NONE);
		composite_md5.setBounds(10, (FIELD_Y * 3) + 20, FIELD_WIDTH, FIELD_HEIGHT);
		composite_md5.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));

		Label label_md5 = new Label(composite_md5, SWT.NONE);
		label_md5.setAlignment(SWT.RIGHT);
		label_md5.setBounds(LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
		label_md5.setFont(font);
		label_md5.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		label_md5.setText(LABEL_TEXT_MD5);

		Button checkbox_md5 = new Button(composite_md5, SWT.CHECK);
		checkbox_md5.setBounds(CHECKBOX_X, CHECKBOX_Y, CHECKBOX_WIDTH, CHECKBOX_HEIGHT);
		checkbox_md5.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		checkbox_md5.setSelection(true);

		Text text_md5 = new Text(composite_md5, SWT.BORDER);
		text_md5.setBounds(TEXT_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT);
		text_md5.setFont(font);

		Button btn_copy_md5 = new Button(composite_md5, SWT.NONE);
		btn_copy_md5.setBounds(BTN_X, BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		btn_copy_md5.setFont(font);
		btn_copy_md5.setText(BTN_TEXT_CP_MD5);
		btn_copy_md5.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection strSel = new StringSelection(text_md5.getText());
				clipboard.setContents(strSel, null);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		/** sha1 **/
		Composite composite_sha1 = new Composite(group_00, SWT.NONE);
		composite_sha1.setBounds(10, (FIELD_Y * 4) + 30, FIELD_WIDTH, FIELD_HEIGHT);
		composite_sha1.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));

		Label label_sha1 = new Label(composite_sha1, SWT.NONE);
		label_sha1.setAlignment(SWT.RIGHT);
		label_sha1.setBounds(LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
		label_sha1.setFont(font);
		label_sha1.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		label_sha1.setText(LABEL_TEXT_SHA1);

		Button checkbox_sha1 = new Button(composite_sha1, SWT.CHECK);
		checkbox_sha1.setBounds(CHECKBOX_X, CHECKBOX_Y, CHECKBOX_WIDTH, CHECKBOX_HEIGHT);
		checkbox_sha1.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		checkbox_sha1.setSelection(true);

		Text text_sha1 = new Text(composite_sha1, SWT.BORDER);
		text_sha1.setBounds(TEXT_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT);
		text_sha1.setFont(font);

		Button btn_copy_sha1 = new Button(composite_sha1, SWT.NONE);
		btn_copy_sha1.setBounds(BTN_X, BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		btn_copy_sha1.setFont(font);
		btn_copy_sha1.setText(BTN_TEXT_CP_SHA1);
		btn_copy_sha1.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection strSel = new StringSelection(text_sha1.getText());
				clipboard.setContents(strSel, null);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		/** sha224 **/
		Composite composite_sha224 = new Composite(group_00, SWT.NONE);
		composite_sha224.setBounds(10, (FIELD_Y * 5) + 40, FIELD_WIDTH, FIELD_HEIGHT);
		composite_sha224.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));

		Label label_sha224 = new Label(composite_sha224, SWT.NONE);
		label_sha224.setAlignment(SWT.RIGHT);
		label_sha224.setBounds(LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
		label_sha224.setFont(font);
		label_sha224.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		label_sha224.setText(LABEL_TEXT_SHA224);

		Button checkbox_sha224 = new Button(composite_sha224, SWT.CHECK);
		checkbox_sha224.setBounds(CHECKBOX_X, CHECKBOX_Y, CHECKBOX_WIDTH, CHECKBOX_HEIGHT);
		checkbox_sha224.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		checkbox_sha224.setSelection(true);

		Text text_sha224 = new Text(composite_sha224, SWT.BORDER);
		text_sha224.setBounds(TEXT_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT);
		text_sha224.setFont(font);

		Button btn_copy_sha224 = new Button(composite_sha224, SWT.NONE);
		btn_copy_sha224.setBounds(BTN_X, BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		btn_copy_sha224.setFont(font);
		btn_copy_sha224.setText(BTN_TEXT_CP_SHA224);
		btn_copy_sha224.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection strSel = new StringSelection(text_sha224.getText());
				clipboard.setContents(strSel, null);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		/** sha256 **/
		Composite composite_sha256 = new Composite(group_00, SWT.NONE);
		composite_sha256.setBounds(10, (FIELD_Y * 6) + 50, FIELD_WIDTH, FIELD_HEIGHT);
		composite_sha256.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));

		Label label_sha256 = new Label(composite_sha256, SWT.NONE);
		label_sha256.setAlignment(SWT.RIGHT);
		label_sha256.setBounds(LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
		label_sha256.setFont(font);
		label_sha256.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		label_sha256.setText(LABEL_TEXT_SHA256);

		Button checkbox_sha256 = new Button(composite_sha256, SWT.CHECK);
		checkbox_sha256.setBounds(CHECKBOX_X, CHECKBOX_Y, CHECKBOX_WIDTH, CHECKBOX_HEIGHT);
		checkbox_sha256.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		checkbox_sha256.setSelection(true);

		Text text_sha256 = new Text(composite_sha256, SWT.BORDER);
		text_sha256.setBounds(TEXT_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT);
		text_sha256.setFont(font);

		Button btn_copy_sha256 = new Button(composite_sha256, SWT.NONE);
		btn_copy_sha256.setBounds(BTN_X, BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		btn_copy_sha256.setFont(font);
		btn_copy_sha256.setText(BTN_TEXT_CP_SHA256);
		btn_copy_sha256.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection strSel = new StringSelection(text_sha256.getText());
				clipboard.setContents(strSel, null);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		/** sha384 **/
		Composite composite_sha384 = new Composite(group_00, SWT.NONE);
		composite_sha384.setBounds(10, (FIELD_Y * 7) + 60, FIELD_WIDTH, FIELD_HEIGHT);
		composite_sha384.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));

		Label label_sha384 = new Label(composite_sha384, SWT.NONE);
		label_sha384.setAlignment(SWT.RIGHT);
		label_sha384.setBounds(LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
		label_sha384.setFont(font);
		label_sha384.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		label_sha384.setText(LABEL_TEXT_SHA384);

		Button checkbox_sha384 = new Button(composite_sha384, SWT.CHECK);
		checkbox_sha384.setBounds(CHECKBOX_X, CHECKBOX_Y, CHECKBOX_WIDTH, CHECKBOX_HEIGHT);
		checkbox_sha384.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		checkbox_sha384.setSelection(true);

		Text text_sha384 = new Text(composite_sha384, SWT.BORDER);
		text_sha384.setBounds(TEXT_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT);
		text_sha384.setFont(font);

		Button btn_copy_sha384 = new Button(composite_sha384, SWT.NONE);
		btn_copy_sha384.setBounds(BTN_X, BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		btn_copy_sha384.setFont(font);
		btn_copy_sha384.setText(BTN_TEXT_CP_SHA384);
		btn_copy_sha384.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection strSel = new StringSelection(text_sha384.getText());
				clipboard.setContents(strSel, null);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		/** sha512 **/
		Composite composite_sha512 = new Composite(group_00, SWT.NONE);
		composite_sha512.setBounds(10, (FIELD_Y * 8) + 70, FIELD_WIDTH, FIELD_HEIGHT);
		composite_sha512.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));

		Label label_sha512 = new Label(composite_sha512, SWT.NONE);
		label_sha512.setAlignment(SWT.RIGHT);
		label_sha512.setBounds(LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
		label_sha512.setFont(font);
		label_sha512.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		label_sha512.setText(LABEL_TEXT_SHA512);

		Button checkbox_sha512 = new Button(composite_sha512, SWT.CHECK);
		checkbox_sha512.setBounds(CHECKBOX_X, CHECKBOX_Y, CHECKBOX_WIDTH, CHECKBOX_HEIGHT);
		checkbox_sha512.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		checkbox_sha512.setSelection(true);

		Text text_sha512 = new Text(composite_sha512, SWT.BORDER);
		text_sha512.setBounds(TEXT_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT);
		text_sha512.setFont(font);

		Button btn_copy_sha512 = new Button(composite_sha512, SWT.NONE);
		btn_copy_sha512.setBounds(BTN_X, BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		btn_copy_sha512.setFont(font);
		btn_copy_sha512.setText(BTN_TEXT_CP_SHA512);
		btn_copy_sha512.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection strSel = new StringSelection(text_sha512.getText());
				clipboard.setContents(strSel, null);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		/** group_01 **/
		Group group_01 = new Group(shlMdSha, SWT.NONE);
		group_01.setText(GROUP_TEXT_01);
		group_01.setFont(font);
		group_01.setBounds(GROUP_X, 290, GROUP_WIDTH_00, GROUP_HEIGHT_01);
		group_01.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));

		/** field 06 **/
		Composite composite_hash = new Composite(group_01, SWT.NONE);
		composite_hash.setBounds(10, FIELD_Y, FIELD_WIDTH, FIELD_HEIGHT);
		composite_hash.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));

		Label label_hash = new Label(composite_hash, SWT.NONE);
		label_hash.setAlignment(SWT.RIGHT);
		label_hash.setBounds(LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
		label_hash.setFont(font);
		label_hash.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));
		label_hash.setText(LABEL_TEXT_HASH);

		Text text_hash = new Text(composite_hash, SWT.BORDER);
		text_hash.setBounds(TEXT_X, TEXT_Y, TEXT_WIDTH, TEXT_HEIGHT);
		text_hash.setFont(font);

		Button btn_paste_hash = new Button(composite_hash, SWT.NONE);
		btn_paste_hash.setBounds(BTN_X, BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		btn_paste_hash.setFont(font);
		btn_paste_hash.setText(BTN_TEXT_PASTE);
		btn_paste_hash.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				Transferable content = clipboard.getContents(null);
				boolean hasStringText = (content != null) && content.isDataFlavorSupported(DataFlavor.stringFlavor);
				if (hasStringText) {
					try {
						String text = (String) content.getTransferData(DataFlavor.stringFlavor);
						text_hash.setText(text);
					} catch (UnsupportedFlavorException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		/** field 07 **/
		Composite composite_verify = new Composite(group_01, SWT.NONE);
		composite_verify.setBounds(10, (FIELD_Y * 2) + 10, FIELD_WIDTH, FIELD_HEIGHT);
		composite_verify.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));

		Button btn_verify = new Button(composite_verify, SWT.NONE);
		btn_verify.setBounds((FIELD_WIDTH / 2 - BTN_WIDTH / 2), BTN_Y, BTN_WIDTH, BTN_HEIGHT);
		btn_verify.setFont(font);
		btn_verify.setText(BTN_TEXT_VERIFY);

		/** field 08 **/
		Composite composite_09 = new Composite(group_01, SWT.NONE);
		composite_09.setBounds(10, (FIELD_Y * 3) + 20, FIELD_WIDTH, FIELD_HEIGHT);
		composite_09.setBackground(Display.getCurrent().getSystemColor(DEFAULT_COLOR));

		Label label_08 = new Label(composite_09, SWT.NONE);
		label_08.setAlignment(SWT.CENTER);
		label_08.setBounds(10, TEXT_Y, FIELD_WIDTH - 20, 24);
		label_08.setFont(boldfont);
		label_08.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
		label_08.setText("");

		btn_verify.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String hash = text_hash.getText();
				if (hash == null || hash == "") {
					label_08.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
					label_08.setText("No hash to match!");
				} else {
					DigestAlgorithmEnum algorithm = null;
					// MD5
					if (hash.equalsIgnoreCase(text_md5.getText())) {
						algorithm = DigestAlgorithmEnum.MD5;
					}
					// SHA-1
					if (hash.equalsIgnoreCase(text_sha1.getText())) {
						algorithm = DigestAlgorithmEnum.SHA1;
					}
					// SHA-224
					if (hash.equalsIgnoreCase(text_sha224.getText())) {
						algorithm = DigestAlgorithmEnum.SHA224;
					}
					// SHA-256
					if (hash.equalsIgnoreCase(text_sha256.getText())) {
						algorithm = DigestAlgorithmEnum.SHA256;
					}
					// SHA-384
					if (hash.equalsIgnoreCase(text_sha384.getText())) {
						algorithm = DigestAlgorithmEnum.SHA384;
					}
					// SHA-512
					if (hash.equalsIgnoreCase(text_sha512.getText())) {
						algorithm = DigestAlgorithmEnum.SHA512;
					}
					if (algorithm != null) {
						String message = String.format("%s Checksums match !", algorithm.getValue());
						label_08.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN));
						label_08.setText(message);
					} else {
						label_08.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
						label_08.setText("This hash does not match!");
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		radio_file.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				btn_browse.setVisible(true);
				progressBar_file.setVisible(true);
				text_00.setVisible(false);
				label_type.setText("File");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		radio_text.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				btn_browse.setVisible(false);
				progressBar_file.setVisible(false);
				text_00.setVisible(true);
				label_type.setText("Text");
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		btn_browse.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				progressBar_file.setSelection(0);
				text_md5.setText("");
				text_sha1.setText("");
				text_sha224.setText("");
				text_sha256.setText("");
				text_sha384.setText("");
				text_sha512.setText("");
				FileDialog fileDialog = new FileDialog(shlMdSha);
				String path = fileDialog.open();

				if (path != null) {
					try {
						File file = new File(path);
						byte[] bytes = Files.readAllBytes(file.toPath());
						GenerateHash generateHash = new CheckSumHash();
						AtomicReference<String> md5Hash = new AtomicReference<>();
						AtomicReference<String> sha1Hash = new AtomicReference<>();
						AtomicReference<String> sha224Hash = new AtomicReference<>();
						AtomicReference<String> sha256Hash = new AtomicReference<>();
						AtomicReference<String> sha384Hash = new AtomicReference<>();
						AtomicReference<String> sha512Hash = new AtomicReference<>();

						ProgressThread progressThread = new ProgressThread(new ProgressThread.TaskProgress() {
							@Override
							public void progress() {
								md5Hash.set(generateHash.MD5(bytes));
								sha1Hash.set(generateHash.SHA1(bytes));
								sha224Hash.set(generateHash.SHA224(bytes));
								sha256Hash.set(generateHash.SHA256(bytes));
								sha384Hash.set(generateHash.SHA384(bytes));
								sha512Hash.set(generateHash.SHA512(bytes));
							}

							@Override
							public void running(long milis, boolean isRunning) {
								Display.getDefault().asyncExec(new Runnable() {
									int count = 0;

									@Override
									public void run() {
										while (count <= 100) {
											progressBar_file.setSelection(count++);
										}
									}
								});
							}
						});
						progressThread.start();

						if (checkbox_md5.getSelection()) {
							text_md5.setText(md5Hash.get());
						}

						if (checkbox_sha1.getSelection()) {
							text_sha1.setText(sha1Hash.get());
						}

						if (checkbox_sha224.getSelection()) {
							text_sha224.setText(sha224Hash.get());
						}

						if (checkbox_sha256.getSelection()) {
							text_sha256.setText(sha256Hash.get());
						}

						if (checkbox_sha384.getSelection()) {
							text_sha384.setText(sha384Hash.get());
						}

						if (checkbox_sha512.getSelection()) {
							text_sha512.setText(sha512Hash.get());
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {

			}
		});

		// event listener
		text_00.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				String text = text_00.getText();
				GenerateHash generateHash = new CheckSumHash();

				if (checkbox_md5.getSelection()) {
					text_md5.setText(generateHash.MD5(text));
				}

				if (checkbox_sha1.getSelection()) {
					text_sha1.setText(generateHash.SHA1(text));
				}

				if (checkbox_sha224.getSelection()) {
					text_sha224.setText(generateHash.SHA224(text));
				}

				if (checkbox_sha256.getSelection()) {
					text_sha256.setText(generateHash.SHA256(text));
				}

				if (checkbox_sha384.getSelection()) {
					text_sha384.setText(generateHash.SHA384(text));
				}

				if (checkbox_sha512.getSelection()) {
					text_sha512.setText(generateHash.SHA512(text));
				}
			}
		});

		shlMdSha.open();
		shlMdSha.layout();
		while (!shlMdSha.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
