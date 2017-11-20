/**
 *                    _ooOoo_
 *                   o8888888o
 *                   88" . "88
 *                   (| -_- |)
 *                    O\ = /O
 *                ____/`---'\____
 *              .   ' \\| |// `.
 *               / \\||| : |||// \
 *             / _||||| -:- |||||- \
 *               | | \\\ - /// | |
 *             | \_| ''\---/'' | |
 *              \ .-\__ `-` ___/-. /
 *           ___`. .' /--.--\ `. . __
 *        ."" '< `.___\_<|>_/___.' >'"".
 *       | | : `- \`.;`\ _ /`;.`/ - ` : | |
 *         \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 *                    `=---='
 *
 * .............................................
 *                佛祖保佑             永无BUG
 */
package cn.leadeon.mfile.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author billy
 * @description TODO
 * @date 2017年8月8日 下午2:55:52
 */
public class EventListener {

	private Collection<MfileListener> listeners;
	
	private ExecutorService executorService =  Executors.newSingleThreadExecutor();

	public EventListener() {
		initListeners();
	}

	public void addConfigurationListener(MfileListener l) {
		if (l == null) {
			throw new IllegalArgumentException("Listener must not be null!");
		}
		listeners.add(l);
	}

	public boolean removeMfileListener(MfileListener l) {
		return listeners.remove(l);
	}

	public Collection<MfileListener> getMfileListeners() {
		return Collections
				.unmodifiableCollection(new ArrayList<MfileListener>(
						listeners));
	}

	public void clearMfileListeners() {
		listeners.clear();
	}

	/**
	 * 
	 * @param fileName
	 * @param props
	 * 
	 * @return void
	 */
	protected void fireEvent(final String fileName, final Map<String, String> props) {
		final Iterator<MfileListener> it = listeners.iterator();
		while (it.hasNext()) {
			final MfileListener listener = it.next();
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					listener.configChanged(fileName, props);
				}
			});
		}
	}

	private void initListeners() {
		listeners = new CopyOnWriteArrayList<MfileListener>();
	}
}
