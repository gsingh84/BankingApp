package external;

import com.bank.model.database.Database;

import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BatchProcessor
{
    private Database database;
    private ExecutorService executor;

    public BatchProcessor()
    {
        database = new Database();
        executor = Executors.newFixedThreadPool(5);
    }

    public void monitorDir()
    {
        try (WatchService service = FileSystems.getDefault().newWatchService())
        {
            Map<WatchKey, Path> map = new HashMap<>();
            Path path = Paths.get("resources/external");

            map.put(path.register(service, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY),
                    path);

            WatchKey watchKey;
            do
            {
                watchKey = service.take();

                if (executor.isShutdown())
                    executor = Executors.newFixedThreadPool(5);

                for (WatchEvent<?> event : watchKey.pollEvents())
                {
                    String kind = event.kind() + "";

                    if (kind.equals("ENTRY_CREATE"))
                        executor.submit(new ReadXml(database, event.context() + ""));
                }

                executor.shutdown();
            } while (watchKey.reset());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
