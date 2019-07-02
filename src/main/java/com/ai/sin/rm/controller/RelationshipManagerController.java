package com.ai.sin.rm.controller;

import com.ai.sin.rm.model.Client;
import com.ai.sin.rm.service.RelationshipManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * REST Controller
 */
@RestController
public class RelationshipManagerController {

    private RelationshipManagerService relationshipManagerService;

    @Autowired
    public RelationshipManagerController(RelationshipManagerService relationshipManagerService) {
        this.relationshipManagerService = relationshipManagerService;
    }

    @GetMapping("/clients/{clientId}/recommendations")
    public Client recommendations(@PathVariable Integer clientId) {
        return relationshipManagerService.getClientRecommendations(clientId);
    }

    @GetMapping("/clients/recommendations")
    public Set<Client> recommendations() {
        return relationshipManagerService.getClientRecommendations();
    }

}
