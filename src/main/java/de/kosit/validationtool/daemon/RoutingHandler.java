package de.kosit.validationtool.daemon;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import lombok.RequiredArgsConstructor;

/**
 * A simple handler which routes between the {@link CheckHandler} and the {@link GuiHandler} depending on the request.
 */
@RequiredArgsConstructor
class RoutingHandler extends BaseHandler {

    private final CheckHandler checkHandler;

    private final GuiHandler guiHandler;

    @Override
    public void handle(final HttpExchange exchange) throws IOException {
        final String requestMethod = exchange.getRequestMethod();
        if (requestMethod.equals("POST")) {
            this.checkHandler.handle(exchange);
        } else if (requestMethod.equals("GET")) {
            this.guiHandler.handle(exchange);
        } else {
            error(exchange, 405, String.format("Method % not supported", requestMethod));
        }
    }
}
