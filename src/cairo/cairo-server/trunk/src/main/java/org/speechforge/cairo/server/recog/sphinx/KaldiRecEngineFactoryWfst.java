package org.speechforge.cairo.server.recog.sphinx;

import java.net.URL;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;
import org.speechforge.cairo.util.pool.ObjectPoolUtil;
import org.speechforge.cairo.util.pool.PoolableObject;

public class KaldiRecEngineFactoryWfst extends SphinxRecEngineFactory {
	
	private static Logger _logger = Logger.getLogger(KaldiRecEngineFactoryWfst.class);
	
	public KaldiRecEngineFactoryWfst(URL sphinxConfigURL) {
		super(sphinxConfigURL);
		// TODO Auto-generated constructor stub
	}

	public PoolableObject makeObject() throws Exception {
        return new KaldiRecEngineWFST(super._cm, super.id++);
    }
	
	public static ObjectPool createObjectPool(URL sphinxConfigURL, int instances) 
			throws InstantiationException {	        
	    if (_logger.isDebugEnabled()) {
	        _logger.debug("creating new rec engine pool... instances: " + instances);
	    }
	
	    PoolableObjectFactory factory = new KaldiRecEngineFactoryWfst(sphinxConfigURL);
	    GenericObjectPool.Config config = ObjectPoolUtil.getGenericObjectPoolConfig(instances);
	
	    ObjectPool objectPool = new GenericObjectPool(factory, config);
	    initPool(objectPool);
	    return objectPool;
	}
	
}
