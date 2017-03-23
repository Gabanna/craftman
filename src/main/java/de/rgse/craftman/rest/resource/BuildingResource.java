package de.rgse.craftman.rest.resource;

import com.google.gson.Gson;
import de.rgse.craftman.model.Building;
import de.rgse.craftman.model.Production;
import de.rgse.craftman.service.BuildingService;
import de.rgse.craftman.service.ProductionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Api
@Path("buildings")
public class BuildingResource {
    
    @Inject
    private BuildingService buildingService;
    
    @Inject
    private ProductionService productionService;
    
    @GET
    @ApiOperation("getBuildings")
    public Response getBuildings(@QueryParam("user") String user) {
        List<Building> buildings = buildingService.getBuildingsForUser(Optional.ofNullable(user));
        
        ResponseBuilder response;
        if (!buildings.isEmpty()) {
            response = Response.ok().entity(new Gson().toJson(buildings)).type(MediaType.APPLICATION_JSON);
            
        } else {
            response = Response.noContent();
        }
        
        return response.build();
    }
    
    @GET
    @Path("{buildingId}")
    @ApiOperation("getBuilding")
    public Response getBuilding(@PathParam("buildingId") String buildingId) {
        Optional<Building> building = buildingService.getBuilding(buildingId);
        
        ResponseBuilder response;
        if (building.isPresent()) {
            response = Response.ok().entity(new Gson().toJson(building.get())).type(MediaType.APPLICATION_JSON);
            
        } else {
            response = Response.noContent();
        }
        
        return response.build();
    }
    
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("create building")
    public Response createBuilding(String buildingJson) {
        ResponseBuilder response;
        
        Optional<String> jsonOpt = Optional.ofNullable(buildingJson).filter(e -> {
            return !e.isEmpty();
        });
        
        if (jsonOpt.isPresent()) {
            Building building = Building.fromJson(jsonOpt.get());
            if (building.getUserOpt().isPresent()) {
                building = buildingService.createBuilding(building);
                response = Response.status(Response.Status.CREATED).entity(building.toJson()).type(MediaType.APPLICATION_JSON);
                
            } else {
                response = Response.status(Response.Status.BAD_REQUEST).entity("The user property is mendatory").type(MediaType.TEXT_PLAIN);
            }
            
        } else {
            response = Response.status(Response.Status.BAD_REQUEST).entity("No body data found").type(MediaType.TEXT_PLAIN);
        }
        
        return response.build();
    }
    
    @GET
    @Path("{buildingId}/productions")
    @ApiOperation("getProduction")
    public Response getProductions(@PathParam("buildingId") String buildingId) {
        List<Production> productions = productionService.getProductionsForBuilding(buildingId);
        
        ResponseBuilder response;
        if (productions.isEmpty()) {
            response = Response.ok().entity(new Gson().toJson(productions)).type(MediaType.APPLICATION_JSON);
            
        } else {
            response = Response.noContent();
        }
        
        return response.build();
    }
    
    @POST
    @Path("{buildingId}/production")
    @ApiOperation("create Production")
    public Response createProduction(@PathParam("buildingId") String buildingId, String productJson) {
        Production production = productionService.createProduction(Production.fromJson(productJson));
        return Response.status(Response.Status.CREATED).entity(production.toJson()).type(MediaType.APPLICATION_JSON).build();
        
    }
}
