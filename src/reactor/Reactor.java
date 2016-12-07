package reactor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * 
 * 反应器模式
 * 
 * Java NIO中
 * 可注册的ServerSocketChannnel相当于Handles 
 * Slector就相当于Synchronous Event Demultiplexer
 * Reactor类，相当于一个Initiation Dispatcher，负责最开始的注册
 * Acceptor类相当于Event Handler
 *
 */



/*
 * 
 */
public class Reactor implements Runnable{

	public final Selector selector;  
    public final ServerSocketChannel serverSocketChannel;  
	
	
    public Reactor(int port) throws IOException{  
        selector=Selector.open();  
        serverSocketChannel=ServerSocketChannel.open();  
        InetSocketAddress inetSocketAddress=new InetSocketAddress(InetAddress.getLocalHost(),port);  
        serverSocketChannel.socket().bind(inetSocketAddress);  
        serverSocketChannel.configureBlocking(false);  

        //向selector注册该channel    
        SelectionKey selectionKey=serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);  

        //利用selectionKey的attache功能绑定Acceptor 如果有事情，触发Acceptor   
        selectionKey.attach(new Acceptor(this));  
    }  
	
	
	@Override
	public void run() {
		try {  
            while(!Thread.interrupted()){  
                selector.select();  
                Set<SelectionKey> selectionKeys= selector.selectedKeys();  
                Iterator<SelectionKey> it=selectionKeys.iterator();  
                //Selector如果发现channel有OP_ACCEPT或READ事件发生，下列遍历就会进行。  
                while(it.hasNext()){  
                    //来一个事件 第一次触发一个accepter线程    
                    //以后触发SocketReadHandler  
                    SelectionKey selectionKey=it.next();  
                    //这相当于Event_handlerd的回调
                    dispatch(selectionKey);  
                    selectionKeys.clear();  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  		
	}

	/*
	 * 当阻塞等待返回时，根据事件发生的Handle将其分发给对应的Event Handler处理
	 * 这里的Event Handler被定义为一个线程绑定到对于的SelectionKey上面
	 * 
	 * 这里的attachment第一次是accepter线程，后面是SocketReadHandler线程
	 */
	private void dispatch(SelectionKey selectionKey) {
        Runnable r = (Runnable)(selectionKey.attachment());    
        if (r != null){    
            r.run();  
        } 		
	}

}
